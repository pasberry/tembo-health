package com.example.customer_data

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

interface PatientRepository: CoroutineCrudRepository<Patient, Long>