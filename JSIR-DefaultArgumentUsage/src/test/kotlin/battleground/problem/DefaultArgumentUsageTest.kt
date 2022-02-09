//@file:JsExport

package battleground.problem

import kotlin.test.Test

class UserService {
    fun createUser(
        name: String, tag: String = name
    ) = "User(name=$name,tag=$tag)"
}

class DefaultArgumentUsageTest {
    private val service = UserService()

    @Test
    fun should_be_able_to_make_a_user_with__tag() {
        println(service.createUser("John", "jo"))
    }
}