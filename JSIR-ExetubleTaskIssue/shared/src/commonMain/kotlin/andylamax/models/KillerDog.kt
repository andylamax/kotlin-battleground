package andylamax.models

import andylamax.behaviours.Dog
import andylamax.behaviours.Killer
import kotlinx.serialization.Serializable

@Serializable
data class KillerDog(val name: String) : Killer, Dog {
    override fun bark() = "$name barks"
    override fun kill() = "$name kills"
}