@file:JsExport

package battleground

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

class SomeWrapper(
    val name: String,
    val http: HttpClient,
    val scope: CoroutineScope
)

fun wrapper(name: String): SomeWrapper {
    console.log("Before operator plus")
    val context = Job() + Dispatchers.Main
    console.log("After operator plus")
    val http = HttpClient(Js) {  }
    console.log("After http client")
    return SomeWrapper(
        name = name,
        http = http,
        scope = CoroutineScope(context + SupervisorJob())
    )
}

