@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground.workarround_02

import kotlinx.browser.window
import kotlin.test.Test
import kotlin.test.assertEquals

@JsExport
external interface State<out T> {
    val value: T
}

@JsExport
external interface MutableState<T> : State<T> {
    override var value: T
}

private class MutableStateImpl<T>(override var value: T) : MutableState<T>


class ManglingImplementationTest {
    @Test
    fun EXPECTED_State_value_property_remains_un_mangled() {
        val state: State<Int> = MutableStateImpl(0)
        window.asDynamic().state = state
        assertEquals(0, window.asDynamic().state.value)
    }

    @Test
    fun EXPECTED_MutableState_value_property_remains_un_mangled() {
        val state: MutableState<Int> = MutableStateImpl(0)
        window.asDynamic().state = state
        window.asDynamic().state.value = 5
        assertEquals(5, window.asDynamic().state.value)
    }
}