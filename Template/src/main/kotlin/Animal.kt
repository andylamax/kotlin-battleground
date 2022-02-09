@file:Suppress("WRONG_EXPORTED_DECLARATION")

@JsExport
open class Animal {
    companion object {
        fun new() = object : Animal() {}
    }

    open fun sound(): String = "common sound"
}

@JsExport
open class DomesticAnimal : Animal() {
    fun shower() = "cleaned"
}

open class Cat : DomesticAnimal()

@JsExport
class ExportedAnimal : Animal() {
    override fun sound() = "exported sound"
}

class NonExportedAnimal : Animal() {
    override fun sound(): String = "non exported sound"
}

@JsExport
class AnimalFarm(val animals: Array<out Animal>)