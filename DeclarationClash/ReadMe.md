## K2/JSIR - `@JSExports` generates clashing declarations for companion objects that extends its own class

Kotlin: 2.0.0-Beta2
Work on: K1/Legacy

Issue: [KT-64364](https://youtrack.jetbrains.com/issue/KT-64364/K2-JSIR-JSExports-generates-clashing-declarations-for-companion-objects-that-extends-its-own-class)

## Problem

When a companion object with a supper class whose clashing declarations have clearly being disambiguated with the `@JsName`
annotation, the compiler still errors with 
`JavaScript name (xxx) generated for this declaration clashes with another declarations`

### Reproducer

Reproducer can be found at [Optional.kt](./src/commonMain/kotlin/kase/Optional.kt)

### Elaboration

Consider the following block of code

```kotlin
interface Optional<out T> {
  @JsName("valueOrThrowException")
  fun valueOrThrow(exp: Throwable): T

  fun valueOrThrow(): T = valueOrThrow(NoSuchElementException("Optional has no value"))
}

abstract class None<out T : Any> private constructor() : Optional<T> {
  @PublishedApi
  internal companion object : None<Nothing>()

  override fun valueOrThrow(exp: Throwable): Nothing = throw exp
}
```

This fails to compile with the error
- `JavaScript name (valueOrThrow) generated for this declaration clashes with another declarations: [fun valueOrThrow(exp: Throwable): Nothing]`
- `JavaScript name (valueOrThrow) generated for this declaration clashes with another declarations: [fun valueOrThrow(): Nothing]`

## Expected Behaviour

This should work the same as it does in Kotlin/JVM,Kotlin/Native or K1 for that matter, or without `@JsExport`

## Workarounds

- ### Avoid using `JsExports`
  The compiler knows how to handle function overloads if the definitions haven't been marked by `JsExport`

- ### Avoid extending subclassing the class with it's companion object

  This problem appears to happen only when the companion object extends its own class

```kotlin
class None<out T : Any> private constructor() : Optional<T> {
    
  @PublishedApi
  internal companion object {
    val instance by lazy { None<Nothing>() }
  }

  override fun valueOrThrow(exp: Throwable): Nothing = throw exp
}
```