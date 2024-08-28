package com.example.data.utils

interface NetflixMapper<Input, Output> {
    fun map(input: Input): Output
}