package com.psytech.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "psychologists")
class Psychologist(
    var firstName: String,
    var lastName: String,
    @Column(unique = true)
    var nationalSecurityNumber: String,
    var birthDate: Date,
    var organization: String,
    @OneToMany(mappedBy = "psychologist")
    var patients: MutableSet<Patient> = HashSet(),
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID? = null
) {

}
