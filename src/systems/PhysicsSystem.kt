package systems

import components.Component
import components.Position
import components.Velocity
import getComponentOf
import kotlin.reflect.KClass

public object Physics: System(listOf(Position::class, Velocity::class), run = { entities ->
    for (e in entities) {
        var pos = getComponentOf(e) as Position//This shouldn't happen here. What if e doesn't have Position?
        val vel = getComponentOf(e) as Velocity

        pos.x += vel.x
        pos.y += vel.y
    }
})