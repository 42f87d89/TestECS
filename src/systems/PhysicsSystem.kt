package systems

import components.Component
import components.Position
import components.Velocity
import getComponentOf
import kotlin.reflect.KClass

public object Physics: System(listOf(Position::class), run = { entities ->
    for (e in entities) {
        var pos = getComponentOf(e, Position::class as KClass<Component>) as Position
        val vel = getComponentOf(e, Velocity::class as KClass<Component>) as Velocity

        pos.x += vel.x
        pos.y += vel.y
    }
})