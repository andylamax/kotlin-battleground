@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground.problem

import expect.expect
import expect.requireFromRootDir
import kotlin.test.Test
import kotlin.test.assertEquals

sealed class WatchMode {
    companion object {
        val DEFAULT = Casually
    }
}

object Eagerly : WatchMode()

typealias Space = String

object Casually : WatchMode() {
    const val Test: Space = "TEST"
}

object WATCH_MODE {
    val EAGERLY by lazy { Eagerly }
    val CASUALLY by lazy { Casually }
    val DEFAULT by lazy { CASUALLY }
}

//class User(
//    var name: String,
//    var email: String
//)

external interface User {
    var name: String
    var email: String
}

object Me {
    fun sayHello(user: User) = "Hello ${user.name}"
    fun printUser(user: User) = "Hello $user"
}

val john: User = jso { name = "James" }

fun logPassword(password: Password) {
    console.log(password)
}

class ObjectExportationTest {
    @Test
    fun should_be_easily_usable_from_js() {
        globalThis.casually = WATCH_MODE.CASUALLY
        assertEquals("TEST", globalThis.casually.Test)
    }

    @Test
    fun user_should_be_able_to_take_good_things() {
        globalThis.me = Me
        val me = globalThis.me
//        val greeting = me.sayHello(jso<dynamic> { name = "Andy" })
//        console.log(jso<dynamic> { email = "email@test.com" })
//        console.log(me.printUser(jso<User> { name = "Jane" }))
//        assertEquals("Hello Andy", greeting)
    }

    @Test
    fun can_be_used_from_javascript() {
        val u = requireFromRootDir<user>("src/test/resources/user.js")

        expect(u.sayHello(jso {
            name = "Lamax"
            email = "zuzu@juju.com"
        })).toBe("Hello Lamax")
    }
}