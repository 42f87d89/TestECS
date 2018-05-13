package systems

import components.Component
import components.Logger
import getComponentOf
import getEntitiesWithComponents
import kotlin.reflect.KClass

fun logger() {
    val es = getEntitiesWithComponents(listOf(Logger::class as KClass<Component>))
    for (e in es) {
        println((getComponentOf(e, Logger::class as KClass<Component>) as Logger).Message)
    }
}