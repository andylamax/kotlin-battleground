## Kotlin Battle Ground

JSIR - `@JSExports` breaks the compiler when function arguments are being used to create other arguments

Kotlin: 1.6.10

## Problem

When a function takes more than one argument, any attempt to use a previous argument to set the value of its successive argument, the JSIR compiler fails with a weird error

### Reproducer

Reproducer can be found at [DefaultArgumentUsageTest](./src/test/kotlin/battleground/problem/DefaultArgumentUsageTest.kt)

### Elaboration

Consider the following block of code

```kotlin
class UserService {
    fun createUser(
        name: String, tag: String = name
    ) = "User(name=$name,tag=$tag)"
}
```

This breaks the compiler and no output can be generated

## Expected Behaviour

This should work the same as it does in Kotlin/JVM and Kotlin/Native, or without `@JsExport`

## Workaround

- ### Avoid using `@JsExport`

  This problem appears to happen only when you mark interfaces and classes with `@JsExport`, so if you can, avoid using `@JsExport`

- ### Use nullables instead

```kotlin
class UserService {
    fun createUser(
        name: String, tag: String? = null
    ) = "User(name=$name,tag=${tag ?: name})"
}
```
