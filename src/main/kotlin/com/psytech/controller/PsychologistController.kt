package com.psytech.controller

import com.psytech.data.PsychologistData
import com.psytech.data.RecommendRequest
import com.psytech.mapper.PsychologistMapper
import com.psytech.service.PsychologistRecommendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/psychologists")
class PsychologistController(
    @Autowired
    open var psychologistRecommendService: PsychologistRecommendService,
    @Autowired
    open var psychologistMapper: PsychologistMapper
) {

    @PostMapping("/recommend")
    fun recommend(@RequestBody request: RecommendRequest): List<PsychologistData> {
        val psychologists = psychologistRecommendService.recommendPsychologists(request.patientId)
        return psychologistMapper.toDataList(psychologists)
    }
}