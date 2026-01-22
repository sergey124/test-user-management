package com.psytech.controller

import com.psytech.data.PsychologistData
import com.psytech.mapper.PsychologistMapper
import com.psytech.service.PsychologistService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/psychologists")
class PsychologistController(
    private val psychologistService: PsychologistService,
    private val psychologistMapper: PsychologistMapper
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): PsychologistData {
        val psychologist = psychologistService.findById(id)
        return psychologistMapper.toData(psychologist)
    }

    @GetMapping
    fun getAll(): List<PsychologistData> {
        val psychologists = psychologistService.findAll()
        return psychologistMapper.toDataList(psychologists)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody data: PsychologistData): PsychologistData {
        val psychologist = psychologistMapper.toEntity(data)
        val saved = psychologistService.save(psychologist)
        return psychologistMapper.toData(saved)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody data: PsychologistData): PsychologistData {
        val psychologist = psychologistMapper.toEntity(data)
        val updated = psychologistService.update(id, psychologist)
        return psychologistMapper.toData(updated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        psychologistService.deleteById(id)
    }
}