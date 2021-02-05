package com.mobapps.plantor.data

public class Event {
    val observers = mutableSetOf<() -> Unit>()

    operator fun plusAssign(observer: () -> Unit) {
        observers.add(observer)
    }

    operator fun minusAssign(observer: () -> Unit) {
        observers.remove(observer)
    }

    operator fun invoke() {
        for (observer in observers)
            observer()
    }
}