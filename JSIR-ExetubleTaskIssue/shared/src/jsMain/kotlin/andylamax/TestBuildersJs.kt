package andylamax

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

@OptIn(DelicateCoroutinesApi::class)
actual fun launchTest(test: suspend CoroutineScope.() -> Any?) = GlobalScope.promise(block = test).unsafeCast<dynamic>()