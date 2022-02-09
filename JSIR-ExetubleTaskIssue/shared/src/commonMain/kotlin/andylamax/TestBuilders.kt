package andylamax

import kotlinx.coroutines.CoroutineScope

expect fun launchTest(test: suspend CoroutineScope.() -> Any?)