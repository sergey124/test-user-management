package com.psytech.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "patients")
class Patient(
    var firstName: String,
    var lastName: String,
    @Column(unique = true)
    var nationalSecurityNumber: String,
    var birthDate: Date,
    var organization: String,
    @ManyToOne @JoinColumn(name = "psychologist_id", referencedColumnName = "id")
    var psychologist: Psychologist? = null,
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID? = null
) {

}
