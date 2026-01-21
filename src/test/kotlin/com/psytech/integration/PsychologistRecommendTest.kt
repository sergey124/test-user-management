package com.psytech.integration

import com.psytech.entity.Patient
import com.psytech.entity.Psychologist
import com.psytech.repository.PsychologistRepository
import com.psytech.service.PsychologistRecommendService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@Transactional
class PsychologistRecommendTest {

    @Autowired
    private lateinit var psychologistRecommendService: PsychologistRecommendService

    @Autowired
    private lateinit var psychologistRepository: PsychologistRepository

    @Autowired
    private lateinit var patientRepository: PatientRepository



    @Test
    fun `when patient free should recommend psychologist with fewer patients`() {
        // Given
        val psy1 = createPsyWithPatients(5)
        val psy2 = createPsyWithPatients(4)

        val unlinkedPatient = createPatient()

        // When
        val recommendedPsys = psychologistRecommendService.recommendPsychologists(
            unlinkedPatient.id.toString())

        // Then
        assertThat(recommendedPsys).hasSize(1)
        val actualPsy = recommendedPsys[0] as Psychologist
        assertThat(actualPsy.id).isEqualTo(psy2.id)
        assertThat(psy1.id).isNotEqualTo(psy2.id)
    }

    @Test
    fun `when patient has psychologist should recommend another available psychologist`() {
        // Given
        val psy1 = createPsyWithPatients(5)
        val psy2 = createPsyWithPatients(3)
        val psy3 = createPsyWithPatients(4)

        val patientWithPsy = createPatient(psy1)

        // When
        val recommendedPsys = psychologistRecommendService.recommendPsychologists(
            patientWithPsy.id.toString()
        )

        // Then
        assertThat(recommendedPsys).hasSize(1)
        val actualPsy = recommendedPsys[0] as Psychologist
        assertThat(actualPsy.id).isEqualTo(psy2.id)
        assertThat(actualPsy.id).isNotEqualTo(psy1.id)
    }

    private fun createPsyWithPatients(numberOfPatients: Int): Psychologist {
        val psychologist = psychologistRepository.save(
            Psychologist(
                firstName = UUID.randomUUID().toString(),
                lastName = UUID.randomUUID().toString(),
                nationalSecurityNumber = UUID.randomUUID().toString(),
                birthDate = Date(),
                organization = UUID.randomUUID().toString()
            )
        )

        repeat(numberOfPatients) {
            createPatient(psychologist)
        }

        return psychologist
    }

    private fun createPatient(psychologist: Psychologist? = null): Patient {
        return patientRepository.save(
            Patient(
                firstName = UUID.randomUUID().toString(),
                lastName = UUID.randomUUID().toString(),
                nationalSecurityNumber = UUID.randomUUID().toString(),
                birthDate = Date(),
                organization = UUID.randomUUID().toString(),
                psychologist = psychologist
            )
        )
    }
}