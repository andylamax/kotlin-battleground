## Kotlin Battle Ground

JSIR - Default Function overloads are broken when marked with `@JSExports`

Kotlin: 1.6.10

## Problem

When an interface (marked with `@JsExport`) overloads functions from other interfaces (also marked with `@JsExport`) with default methods, calling those methods on an instance of that interface leads
to a `RangeError: Maximum call stack size exceeded`

### Reproducer

Reproducer can be found at [FunctionOverloadProblemTest](./src/test/kotlin/battleground/problem/FunctionOverloadProblemTest.kt)

### Elaboration

Consider the following class hierarchy

```kotlin
@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground

import kotlin.test.Test
import kotlin.test.assertTrue

interface HasId {
    val uid: String

    @JsName("hasIdCopy")
    fun copy(id: String): HasId
}

interface Deletable {
    val deleted: Boolean

    @JsName("deletableCopy")
    fun copy(deleted: Boolean): Deletable
}

interface Savable : HasId, Deletable {
    override fun copy(id: String) = copy(id = uid, deleted = deleted)
    override fun copy(deleted: Boolean) = copy(id = uid, deleted = deleted)

    @JsName("savableCopy")
    fun copy(id: String, deleted: Boolean): Savable
}

data class User(
    val name: String = "John Doe",
    override val uid: String = "",
    override val deleted: Boolean = false
) : Savable {
    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}
```

And then

```kotlin
val user = User()
user.copy(id = "test-id") // throws, because copy is overloaded in Savable, if it was overloaded in User it wouldn't throw
```

## Expected Behaviour

Overloading should work the same as it does in Kotlin/JVM and Kotlin/Native

## Workaround

- ### Avoid using `@JsExport`

  This problem appears to happen only when you mark interfaces and classes with `@JsExport`, so if you can, avoid using `@JsExport`. Coz as show
  in [RemoveJsExportWorkaroundTest](./src/test/kotlin/battleground/workaround_01/RemoveJsExportWorkaroundTest.kt), it just happens to work

- ### Avoid function overloads

The best way to not have a problem is to not have it at all. see [AvoidOverloadingWorkaroundTest](./src/test/kotlin/battleground/workaround_02/AvoidOverloadingWorkaroundTest.kt)
