package com.alaishat.ahmed.mobostore.utils

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * Mobo Store Project.
 */
internal fun <E> MutableSet<E>.addOrRemove(element: E) {
    if (!add(element)) {
        remove(element)
    }
}
