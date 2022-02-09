package andylamax

import kotlinx.coroutines.delay
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.expect
import kotlin.test.fail

class HumanTest {
    @Test
    fun should_create_a_person_instance() {
        println("Started\n")
        assertEquals(Human("John", 19), Human("John", 19))
        println("Ended\n")
    }

    @Test
    fun suspending_test_should_fail() = launchTest {
        println("Running before suspending\n")
        delay(1000)
        assertEquals(2, 1 + 1)
        println("Ended after suspending\n")
    }
}