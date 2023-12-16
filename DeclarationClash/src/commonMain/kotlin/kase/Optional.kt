@file:JsExport

package kase

import kotlin.js.JsExport
import kotlin.js.JsName

interface Optional<out T> {
    @JsName("valueOrThrowException")
    fun valueOrThrow(exp: Throwable): T

    fun valueOrThrow(): T = valueOrThrow(NoSuchElementException("Optional has no value"))
}