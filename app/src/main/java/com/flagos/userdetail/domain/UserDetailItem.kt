package com.flagos.userdetail.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailItem(
    @field:Json(name = "results") var results: List<Results> = arrayListOf(),
    @field:Json(name = "info") var info: Info? = Info()
)

@JsonClass(generateAdapter = true)
data class Name(
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "first") var first: String? = null,
    @field:Json(name = "last") var last: String? = null
)

@JsonClass(generateAdapter = true)
data class Street(
    @field:Json(name = "number") var number: Int? = null,
    @field:Json(name = "name") var name: String? = null
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    @field:Json(name = "latitude") var latitude: String? = null,
    @field:Json(name = "longitude") var longitude: String? = null
)

@JsonClass(generateAdapter = true)
data class Timezone(
    @field:Json(name = "offset") var offset: String? = null,
    @field:Json(name = "description") var description: String? = null
)

@JsonClass(generateAdapter = true)
data class Location(
    @field:Json(name = "street") var street: Street? = Street(),
    @field:Json(name = "city") var city: String? = null,
    @field:Json(name = "state") var state: String? = null,
    @field:Json(name = "country") var country: String? = null,
    @field:Json(name = "postcode") var postcode: Int? = null,
    @field:Json(name = "coordinates") var coordinates: Coordinates? = Coordinates(),
    @field:Json(name = "timezone") var timezone: Timezone? = Timezone()
)

@JsonClass(generateAdapter = true)
data class Login(
    @field:Json(name = "uuid") var uuid: String? = null,
    @field:Json(name = "username") var username: String? = null,
    @field:Json(name = "password") var password: String? = null,
    @field:Json(name = "salt") var salt: String? = null,
    @field:Json(name = "md5") var md5: String? = null,
    @field:Json(name = "sha1") var sha1: String? = null,
    @field:Json(name = "sha256") var sha256: String? = null
)

@JsonClass(generateAdapter = true)
data class Dob(
    @field:Json(name = "date") var date: String? = null,
    @field:Json(name = "age") var age: Int? = null

)

@JsonClass(generateAdapter = true)
data class Registered(
    @field:Json(name = "date") var date: String? = null,
    @field:Json(name = "age") var age: Int? = null
)

@JsonClass(generateAdapter = true)
data class Id(
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "value") var value: String? = null
)

@JsonClass(generateAdapter = true)
data class Picture(
    @field:Json(name = "large") var large: String? = null,
    @field:Json(name = "medium") var medium: String? = null,
    @field:Json(name = "thumbnail") var thumbnail: String? = null
)

@JsonClass(generateAdapter = true)
data class Results(
    @field:Json(name = "gender") var gender: String? = null,
    @field:Json(name = "name") var name: Name? = Name(),
    @field:Json(name = "location") var location: Location? = Location(),
    @field:Json(name = "email") var email: String? = null,
    @field:Json(name = "login") var login: Login? = Login(),
    @field:Json(name = "dob") var dob: Dob? = Dob(),
    @field:Json(name = "registered") var registered: Registered? = Registered(),
    @field:Json(name = "phone") var phone: String? = null,
    @field:Json(name = "cell") var cell: String? = null,
    @field:Json(name = "id") var id: Id? = Id(),
    @field:Json(name = "picture") var picture: Picture? = Picture(),
    @field:Json(name = "nat") var nat: String? = null
)

@JsonClass(generateAdapter = true)
data class Info(
    @field:Json(name = "seed") var seed: String? = null,
    @field:Json(name = "results") var results: Int? = null,
    @field:Json(name = "page") var page: Int? = null,
    @field:Json(name = "version") var version: String? = null
)