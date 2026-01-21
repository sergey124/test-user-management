package com.psytech.mapper

import com.psytech.data.PsychologistData
import com.psytech.entity.Psychologist
import org.springframework.stereotype.Component

@Component
class PsychologistMapper {
    fun toData(psychologist: Psychologist): PsychologistData {
        return PsychologistData(
            id = psychologist.id,
            firstName = psychologist.firstName,
            lastName = psychologist.lastName,
            nationalSecurityNumber = psychologist.nationalSecurityNumber,
            birthDate = psychologist.birthDate,
            organization = psychologist.organization
        )
    }

    fun toDataList(psychologists: List<Psychologist>): List<PsychologistData> {
        return psychologists.map { toData(it) }
    }
}
