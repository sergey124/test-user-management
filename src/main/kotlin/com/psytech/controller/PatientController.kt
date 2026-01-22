package com.psytech.controller
import com.psytech.data.PatientData
import com.psytech.mapper.PatientMapper
import com.psytech.service.PatientService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID
@RestController
@RequestMapping("/patients")
class PatientController(
    private val patientService: PatientService,
    private val patientMapper: PatientMapper
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): PatientData {
        val patient = patientService.findById(id)
        return patientMapper.toData(patient)
    }
    @GetMapping
    fun getAll(): List<PatientData> {
        val patients = patientService.findAll()
        return patientMapper.toDataList(patients)
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody data: PatientData): PatientData {
        val patient = patientMapper.toEntity(data)
        val saved = patientService.save(patient)
        return patientMapper.toData(saved)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody data: PatientData): PatientData {
        val patient = patientMapper.toEntity(data)
        val updated = patientService.update(id, patient)
        return patientMapper.toData(updated)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        patientService.deleteById(id)
    }
}
