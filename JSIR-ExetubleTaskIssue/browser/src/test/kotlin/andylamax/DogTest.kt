package andylamax

import andylamax.models.KillerDog
import andylamax.modles.RobotDog
import kotlinx.coroutines.delay
import kotlin.test.Test
import kotlin.test.assertEquals

class DogTest {
    @Test
    fun robot_dog_ang_killer_dog_should_all_bark() {
        assertEquals(RobotDog("Spike").bark(), KillerDog("Spike").bark())
    }

    @Test
    fun should_fail() {
        assertEquals(1 + 1, 2)
    }

    @Test
    fun suspend_functions_should_fail() = launchTest {
        delay(1500)
        assertEquals(1 + 1, 2)
    }
}