package com.psytech.mapper
import com.psytech.data.PatientData
import com.psytech.entity.Patient
import com.psytech.repository.PsychologistRepository
import org.springframework.stereotype.Component
@Component
class PatientMapper(
    private val psychologistRepository: PsychologistRepository
) {
    fun toData(patient: Patient): PatientData {
        return PatientData(
            id = patient.id,
            firstName = patient.firstName,
            lastName = patient.lastName,
            nationalSecurityNumber = patient.nationalSecurityNumber,
            birthDate = patient.birthDate,
            organization = patient.organization,
            psychologistId = patient.psychologist?.id
        )
    }
    fun toDataList(patients: List<Patient>): List<PatientData> {
        return patients.map { toData(it) }
    }
    fun toEntity(data: PatientData): Patient {
        val psychologist = data.psychologistId?.let { 
            psychologistRepository.findById(it).orElse(null)
        }
        return Patient(
            id = data.id,
            firstName = data.firstName,
            lastName = data.lastName,
            nationalSecurityNumber = data.nationalSecurityNumber,
            birthDate = data.birthDate,
            organization = data.organization,
            psychologist = psychologist
        )
    }
}
