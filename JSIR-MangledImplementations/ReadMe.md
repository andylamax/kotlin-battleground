## Kotlin Battle Ground

JSIR - `@JSExports` still mangles properties even when marked as `@JsExport`

Kotlin: 1.6.10

Fixed: 1.6.20-M1

Issue: [KT-45727](https://youtrack.jetbrains.com/issue/KT-45727)

## Prelude

Normally we'd like to have interfaces or abstract classes as our public API, and have underlying implementations private/internal to the module.

## Problem

With kotlin multiplatform and kotlin js, this is still a huge challenge because despite using `@JsExport`, properties of interfaces/abstract classes, are still mangled. It should be known that methods
are not mangled at all

### Reproducer

Reproducer can be found at [ManglingImplementationTest](./src/test/kotlin/battleground/problem/ManglingImplementationTest.kt)

### Elaboration

Consider the following block of code

```kotlin
@JsExport
interface State<out T> {
    val value: T
}

@JsExport
interface MutableState<T> : State<T> {
    override var value: T
}

class MutableStateImpl<T>(override var value: T) : MutableState<T>
```

would cause this test to fail

```kotlin
val state: State<Int> = MutableStateImpl(0)
window.asDynamic().state = state
assertEquals(0, window.asDynamic().state.value) // expected: 0, actual: undefined
```

## Expected Behaviour

This test should pass since `State` was marked with `@JsExport`, therefore, `MutableStateImpl` should respect, State's export as it implements it

```kotlin
val state: State<Int> = MutableStateImpl(0)
window.asDynamic().state = state
assertEquals(0, window.asDynamic().state.value) // expected: 0, actual: undefined
```

## Workaround

- ### Avoid using properties at the moment

  This problem appears to happen only when you are dealing with properties, methods names are preserved as show below.

  ```kotlin
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
  ```
  See [UseMethodsWorkaroundTest](src/test/kotlin/battleground/workarround_01/UseMethodsWorkaroundTest.kt)

- ### Use external interfaces

  If you happen to be in pure js environment, I would urge you to use external interfaces as show
  in [UseMethodsWorkaroundTest](src/test/kotlin/battleground/workarround_02/UseExternalInterfacesWorkaroundTest.kt). However, this gets tricky when in a multiplatform codebase coz you would end up
  writing trival expect/actual interfaces just to preserve JS/TS interoperability.

  External Interface Sample Below
  ```kotlin
  @JsExport
  external interface State<out T> {
    val value: T
  }
  
  @JsExport
  external interface MutableState<T> : State<T> {
    override var value: T
  }
  
  private class MutableStateImpl<T>(override var value: T) : MutableState<T>
  ```
