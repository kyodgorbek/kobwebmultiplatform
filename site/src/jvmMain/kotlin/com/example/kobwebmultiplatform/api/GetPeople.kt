package com.example.kobwebmultiplatform.api

import com.example.kobwebmultiplatform.ApiResponse
import com.example.kobwebmultiplatform.Person
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val people = listOf(
    Person(name = "Yodgorbek", age = 25),
    Person(name = "Ilhom", age = 24),
    Person(name = "Anvar", age = 23),
    Person(name = "Dilshod", age = 22),
)

@Api
suspend fun getPeople(context: ApiContext) {
    try {
        val number = context.req.params.getValue("count").toInt()
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Success(data = people.take(number))
            )
        )
    } catch (e: Exception) {
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Error(errorMessage = e.message.toString())
            )
        )
    }
}