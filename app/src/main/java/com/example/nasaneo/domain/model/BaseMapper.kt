package com.example.nasaneo.domain.model

interface BaseMapper<From, To> {
    fun map(from: From): To
}