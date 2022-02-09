@file:Suppress("WRONG_EXPORTED_DECLARATION")

import kotlinx.browser.window
import kotlin.test.Test
import kotlin.test.assertEquals

class ManglingTest {
    @Test
    fun exported_animal_does_not_mangle() {
        val animal: Animal = ExportedAnimal()
        window.asDynamic().animal = animal
        console.log(animal)
        val sound: String = window.asDynamic().animal.sound()
        assertEquals(sound, "exported sound")
    }

    @Test
    fun non_exported_animal_mangles() {
        val animal: Animal = NonExportedAnimal()
        window.asDynamic().animal = animal
        console.log(animal)
        console.dir(animal)
        val sound: String = window.asDynamic().animal.sound()
        assertEquals(sound, "non exported sound")
    }

    @Test
    fun usable_from_farm() {
        val farm = AnimalFarm(
            arrayOf(
                Animal.new(),
                ExportedAnimal(),
                NonExportedAnimal(),
                DomesticAnimal(),
                Cat()
            )
        )
        window.asDynamic().farm = farm
        window.asDynamic().farm.animals.forEach({ animal ->
            console.log(animal.sound())
        })
    }
}