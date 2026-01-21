package com.psytech.data

import java.util.*

data class PsychologistData(
    val id: UUID?,
    val firstName: String,
    val lastName: String,
    val nationalSecurityNumber: String,
    val birthDate: Date,
    val organization: String
)
