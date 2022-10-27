package com.alaishat.ahmed.jetstore.utils

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * JetStore Project.
 */
internal fun <E> MutableSet<E>.addOrRemove(element: E) {
    if (!add(element)) {
        remove(element)
    }
}
