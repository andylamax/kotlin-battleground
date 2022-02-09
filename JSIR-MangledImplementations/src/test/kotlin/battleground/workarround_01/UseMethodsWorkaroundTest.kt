@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground.workarround_01

import kotlinx.browser.window
import kotlin.test.Test
import kotlin.test.assertEquals

@JsExport
interface State<out T> {
    fun getValue(): T
}

@JsExport
interface MutableState<T> : State<T> {
    fun setValue(value: T)
}

private class MutableStateImpl<T>(private var value: T) : MutableState<T> {
    override fun getValue(): T = value

    override fun setValue(value: T) {
        this.value = value
    }
}


class ManglingImplementationTest {
    @Test
    fun EXPECTED_State_getValue_method_remains_un_mangled() {
        val state: State<Int> = MutableStateImpl(0)
        window.asDynamic().state = state
        assertEquals(0, window.asDynamic().state.getValue())
    }

    @Test
    fun EXPECTED_MutableState_setValue_method_remains_un_mangled() {
        val state: MutableState<Int> = MutableStateImpl(0)
        window.asDynamic().state = state
        window.asDynamic().state.setValue(5)
        assertEquals(5, window.asDynamic().state.getValue())
    }
}