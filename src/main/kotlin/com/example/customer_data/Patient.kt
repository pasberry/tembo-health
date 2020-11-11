package com.example.customer_data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("PATIENT")
data class Patient(@Id val id : Long? = null, val firstName : String, val lastName : String, val dateOfBirth : String, val phoneNumber : String )
