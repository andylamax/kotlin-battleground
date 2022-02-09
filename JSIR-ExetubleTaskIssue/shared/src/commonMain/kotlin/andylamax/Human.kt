package andylamax

import kotlinx.serialization.Serializable

@Serializable
data class Human(
    val name: String,
    val age: Int
) : Creature