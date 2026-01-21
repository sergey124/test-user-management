package com.psytech

import com.psytech.entity.Psychologist
import com.psytech.repository.PsychologistRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class Application {
	@Bean
	fun initData(psychologistRepository: PsychologistRepository) = CommandLineRunner {
		if (psychologistRepository.count() == 0L) {
			val psychologist = Psychologist(
				firstName = "John",
				lastName = "Doe",
				nationalSecurityNumber = "123-45-6789",
				birthDate = Date(90, 0, 1),
				organization = "Mental Health Clinic"
			)
			psychologistRepository.save(psychologist)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}