package com.psytech.repository

import com.psytech.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PatientRepository : JpaRepository<Patient, UUID>
