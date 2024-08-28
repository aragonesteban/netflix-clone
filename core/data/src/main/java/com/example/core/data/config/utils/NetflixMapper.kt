package com.example.core.data.config.utils

interface NetflixMapper<Input, Output> {
    fun map(input: Input): Output
}