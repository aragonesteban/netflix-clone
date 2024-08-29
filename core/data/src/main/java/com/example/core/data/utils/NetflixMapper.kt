package com.example.core.data.utils

interface NetflixMapper<Input, Output> {
    fun map(input: Input): Output
}