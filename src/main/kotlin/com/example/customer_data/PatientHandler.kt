package com.example.customer_data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.json



@Component
class PatientHandler(private val patientRepository: PatientRepository) {

    suspend fun handlePatient(request: ServerRequest) : ServerResponse {

        val id = request.pathVariable("id")

        val patient:Patient? = patientRepository.findById(id.toLong())

        patient?.let {
            return ServerResponse.ok().json().bodyValueAndAwait(it)
        }

        return ServerResponse.notFound().buildAndAwait()
    }

    suspend fun handleAllPatients(request: ServerRequest) : ServerResponse {

        val patients: Flow<Patient> = patientRepository.findAll()

        return patients?.let {
            ServerResponse.ok().bodyValueAndAwait(it.toList())
        }

        return ServerResponse.notFound().buildAndAwait()
    }

    suspend fun handleCreatePatient(request: ServerRequest) : ServerResponse {

        val patient:Patient? = request.awaitBody()

        patient?.let {
            patientRepository.save(patient)
            return ServerResponse.status(HttpStatus.CREATED).buildAndAwait()
        }

        return ServerResponse.badRequest().buildAndAwait()

    }

    suspend fun handleDeletePatient(request: ServerRequest) : ServerResponse {

        val id = request.pathVariable("id")

        patientRepository.deleteById(id.toLong())

        return ServerResponse.ok().buildAndAwait()

    }

}