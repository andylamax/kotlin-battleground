@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package battleground.workaround_02

import kotlin.test.Test

interface HasId {
    val uid: String
    fun copyId(id: String): HasId
}

interface Deletable {
    val deleted: Boolean
    fun copyDeleted(deleted: Boolean): Deletable
}

interface Savable : HasId, Deletable {
    override fun copyId(id: String) = copySavable(id = uid, deleted = deleted)
    override fun copyDeleted(deleted: Boolean) = copySavable(id = uid, deleted = deleted)

    fun copySavable(id: String, deleted: Boolean): Savable
}

data class User(
    val name: String = "John Doe",
    override val uid: String = "",
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}

class AvoidOverloadingWorkAroundTest {

    @Test
    fun calling_function_does_not_throw() {
        val user = User()
        user.copyId(id = "test-id")
        user.copyDeleted(deleted = true)
        user.copySavable(id = "one", deleted = true)
    }
}