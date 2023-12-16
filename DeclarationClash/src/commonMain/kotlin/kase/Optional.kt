@file:JsExport

package kase

import kotlin.js.JsExport
import kotlin.js.JsName

interface Optional<out T> {
    @JsName("valueOrThrowException")
    fun valueOrThrow(exp: Throwable): T

    fun valueOrThrow(): T = valueOrThrow(NoSuchElementException("Optional has no value"))
}


abstract class None<out T : Any> private constructor() : Optional<T> {
    @PublishedApi
    internal companion object : None<Nothing>()

    /** // Workaround
    @PublishedApi
    internal companion object {
        val instance by lazy { None<Nothing>() }
    }
    */

    override fun valueOrThrow(exp: Throwable): Nothing = throw exp
}