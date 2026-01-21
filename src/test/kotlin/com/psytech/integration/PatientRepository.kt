package com.psytech.integration

import com.psytech.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PatientRepository : JpaRepository<Patient, UUID> {
}
