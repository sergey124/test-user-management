package com.psytech.controller

import com.psytech.data.PsychologistData
import com.psytech.data.RecommendRequest
import com.psytech.mapper.PsychologistMapper
import com.psytech.service.PsychologistRecommendService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recommendations")
class PsychologistRecommendationController(
    private val psychologistRecommendService: PsychologistRecommendService,
    private val psychologistMapper: PsychologistMapper
) {

    @PostMapping("/psychologists")
    fun recommendPsychologists(@RequestBody request: RecommendRequest): List<PsychologistData> {
        val psychologists = psychologistRecommendService.recommendPsychologists(request.patientId)
        return psychologistMapper.toDataList(psychologists)
    }
}
