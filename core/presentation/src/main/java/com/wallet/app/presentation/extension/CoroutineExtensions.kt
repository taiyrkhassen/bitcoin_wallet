package com.wallet.app.presentation.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Boolean
import kotlin.Throwable
import kotlin.Unit

fun <T> Flow<T>.subscribe(
    scope: CoroutineScope,
    doOnEach: (suspend (T) -> Unit)? = null,
    doOnError: (suspend (Throwable) -> Unit)? = null
): Job {
    return onEach { doOnEach?.invoke(it) }
        .catch {
            doOnError?.invoke(it)
        }
        .launchIn(scope)
}

private sealed class Result {
    class Success<T>(val value: T) : Result()
    class Error(val throwable: Throwable) : Result()
}

fun <T> CoroutineScope.subscribe(
    doAction: suspend () -> T,
    doOnError: (suspend (Throwable) -> Unit)? = null,
    doOnSuccess: (suspend (T) -> Unit)? = null,
) = launch(Dispatchers.Main) {
    val result = withContext(Dispatchers.IO) {
        try {
            Result.Success(doAction())
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }
    when (result) {
        is Result.Success<*> -> doOnSuccess?.invoke(result.value as T)
        is Result.Error -> {
            doOnError?.invoke(result.throwable)
        }
    }
}