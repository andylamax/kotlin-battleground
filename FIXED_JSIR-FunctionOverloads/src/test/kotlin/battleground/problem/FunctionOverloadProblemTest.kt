@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground.problem

import expect.expectFunction
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

// The interface overloading other interfaces
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

class FunctionOverloadsProblemTest {

    @Test
    fun calling_an_overloaded_function_throws_maximum_stack_size_exceeded_error() {
        val cause = expectFunction {
            val user = User()
            user.copy(id = "test-id")
        }.toFail()
        assertTrue("Did not exceed maximum call stack size as expected") {
            cause.message?.contains("Maximum call stack size exceeded") == true
        }
    }

    @Test
    fun calling_a_none_overloaded_does_not_throw() {
        val user = User()
        println(user.copy(uid = "good"))
    }
}