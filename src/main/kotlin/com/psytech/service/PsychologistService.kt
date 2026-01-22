package com.psytech.service

import com.psytech.entity.Psychologist
import com.psytech.repository.PsychologistRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PsychologistService(
    private val psychologistRepository: PsychologistRepository
) {

    fun findById(id: UUID): Psychologist {
        return psychologistRepository.findById(id)
            .orElseThrow { NoSuchElementException("Psychologist with id $id not found") }
    }

    fun findAll(): List<Psychologist> {
        return psychologistRepository.findAll()
    }

    fun save(psychologist: Psychologist): Psychologist {
        return psychologistRepository.save(psychologist)
    }

    fun update(id: UUID, psychologist: Psychologist): Psychologist {
        val existing = findById(id)
        existing.firstName = psychologist.firstName
        existing.lastName = psychologist.lastName
        existing.nationalSecurityNumber = psychologist.nationalSecurityNumber
        existing.birthDate = psychologist.birthDate
        existing.organization = psychologist.organization
        return psychologistRepository.save(existing)
    }

    fun deleteById(id: UUID) {
        psychologistRepository.deleteById(id)
    }
}
