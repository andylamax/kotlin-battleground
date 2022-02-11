package battleground.problem

external val globalThis: dynamic

inline fun <T> jso(builder: T.() -> Unit = {}) = js("{}").unsafeCast<T>().apply(builder)