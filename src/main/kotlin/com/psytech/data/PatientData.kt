package com.psytech.data

import java.util.*

data class PatientData(
    val id: UUID? = null,
    val firstName: String,
    val lastName: String,
    val nationalSecurityNumber: String,
    val birthDate: Date,
    val organization: String,
    val psychologistId: UUID? = null
)
