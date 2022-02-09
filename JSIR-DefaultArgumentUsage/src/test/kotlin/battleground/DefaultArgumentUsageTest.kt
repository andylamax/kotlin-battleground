package battleground

import kotlin.test.Test

interface UserService {
    companion object {
        fun createUser(
            name: String, tag: String = name
        ) = "User(name=$name,tag=$tag)"
    }
}

interface Greeter {
    fun greet(name: String, times: Int = name.length) = List(times) { "Hello $name" }.joinToString(separator = "\n")
}

class DefaultArgumentUsageTest {
    private val service = object : UserService {}
    private val greeter = object : Greeter {}

    @Test
    fun should_be_able_to_make_a_user_with__tag() {
        println(UserService.createUser("John", "jo"))
    }

    @Test
    fun should_be_able_to_greet_multiple_times() {
        println(greeter.greet("Andy", 1))
    }
}