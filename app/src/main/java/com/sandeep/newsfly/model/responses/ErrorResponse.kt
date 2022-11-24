

package com.sandeep.newsfly.model.responses

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)