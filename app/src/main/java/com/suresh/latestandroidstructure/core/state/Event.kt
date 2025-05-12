package com.suresh.latestandroidstructure.core.state

// core/state/Event.kt
class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) null else {
            hasBeenHandled = true
            content
        }

    fun peekContent(): T = content
}