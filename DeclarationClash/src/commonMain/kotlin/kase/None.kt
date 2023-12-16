@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kase

import kotlin.js.JsExport

abstract class None<out T : Any> private constructor() : Optional<T> {
    @PublishedApi
    internal companion object : None<Nothing>()

    override fun valueOrThrow(exp: Throwable): Nothing = throw exp
}