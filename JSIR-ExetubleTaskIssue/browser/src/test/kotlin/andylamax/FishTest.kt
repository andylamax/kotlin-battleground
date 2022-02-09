package andylamax

import kotlin.test.Test
import kotlin.test.assertEquals

class FishTest {
    @Test
    fun should_have_equal_things() {
        assertEquals(Fish("tilapia"), Fish("tilapia"))
    }
}