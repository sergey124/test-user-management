package com.psytech.service
import com.psytech.entity.Patient
import com.psytech.repository.PatientRepository
import com.psytech.repository.PsychologistRepository
import org.springframework.stereotype.Service
import java.util.UUID
@Service
class PatientService(
    private val patientRepository: PatientRepository,
    private val psychologistRepository: PsychologistRepository
) {
    fun findById(id: UUID): Patient {
        return patientRepository.findById(id)
            .orElseThrow { NoSuchElementException("Patient with id $id not found") }
    }
    fun findAll(): List<Patient> {
        return patientRepository.findAll()
    }
    fun save(patient: Patient): Patient {
        return patientRepository.save(patient)
    }
    fun update(id: UUID, patient: Patient): Patient {
        val existing = findById(id)
        existing.firstName = patient.firstName
        existing.lastName = patient.lastName
        existing.nationalSecurityNumber = patient.nationalSecurityNumber
        existing.birthDate = patient.birthDate
        existing.organization = patient.organization
        existing.psychologist = patient.psychologist
        return patientRepository.save(existing)
    }
    fun deleteById(id: UUID) {
        patientRepository.deleteById(id)
    }
}
