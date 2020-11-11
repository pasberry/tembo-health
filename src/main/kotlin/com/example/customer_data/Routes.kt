package com.example.customer_data

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routes  {

    @Bean
    fun router(patientHandler: PatientHandler) = coRouter {

        GET("/patient/{id}", patientHandler::handlePatient)
        GET("/patient", patientHandler::handleAllPatients)
        POST("/patient", patientHandler::handleCreatePatient)
        DELETE("/patient/{id}", patientHandler::handleDeletePatient)
    }

}

