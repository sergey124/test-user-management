package com.psytech.repository

import com.psytech.entity.Psychologist
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface PsychologistRepository : JpaRepository<Psychologist, UUID> {
    /**
     * @param maxPatients: Int - max number of patients, exclusive
     */
    @Query("""
        SELECT p 
        FROM Psychologist p 
        WHERE SIZE(p.patients) < :maxPatients 
        ORDER BY SIZE(p.patients)
        """)
    fun findByPatientsLessThan(maxPatients: Int, pageable: Pageable): List<Psychologist>
}