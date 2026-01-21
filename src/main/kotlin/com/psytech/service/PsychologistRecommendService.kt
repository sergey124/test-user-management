package com.psytech.service

import com.psytech.entity.Psychologist
import com.psytech.repository.PsychologistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

private const val MAX_PATIENTS_FOR_PSYCHOLOGIST = 5

@Component
class PsychologistRecommendService (
    @Autowired
    open var psychologistRepository: PsychologistRepository
) {
    fun recommendPsychologists(patientId: String): List<Psychologist> {
        //TODO: add personalized recommendation for patientId
        return psychologistRepository.findByPatientsLessThan(
            maxPatients = MAX_PATIENTS_FOR_PSYCHOLOGIST,
            pageable = PageRequest.of(0, 1)
        )
    }
}