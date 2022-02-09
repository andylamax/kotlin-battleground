@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground.problem

import kotlinx.browser.window
import kotlin.test.Test
import kotlin.test.assertEquals

@JsExport
interface State<out T> {
    val value: T
//        @JsName("getValueNow") get // this exposes a getValueNow method, but it is not in type definitions

    fun getCurrentValue(): T = value
}

@JsExport
interface MutableState<T> : State<T> {
    override var value: T

    fun setCurrentValue(currentValue: T) {
        value = currentValue
    }
}

private class MutableStateImpl<T>(override var value: T) : MutableState<T>

class ManglingImplementationTest {
    @Test
    fun EXPECTED_state_getCurrentValue_method_remains_un_mangled() {
        val state: State<Int> = MutableStateImpl(0)
        window.asDynamic().state = state
        assertEquals(0, window.asDynamic().state.getCurrentValue())
    }

    @Test
    fun UNEXPECTED_state_value_property_becomes_mangled() {
        val state: State<Int> = MutableStateImpl(0)
        window.asDynamic().state = state
        assertEquals(undefined, window.asDynamic().state.value)
    }
}