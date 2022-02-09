package andylamax.modles

import andylamax.behaviours.Dog
import andylamax.behaviours.Robot

data class RobotDog(
    val name: String
) : Robot, Dog {
    override fun bark() = "$name barks"

    override fun drive() = "$name drive"
}
