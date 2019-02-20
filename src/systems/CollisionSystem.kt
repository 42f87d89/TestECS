package systems

import components.CollisionRect
import components.Position
import getComponentOf

public object CollisionSystem: System(listOf(CollisionRect::class, Position::class), run = { entities ->
    val sorted = entities.sortedBy { e ->
        val pos = getComponentOf(e) as Position
        pos.x
    }
    sorted.filter { e ->
        val pos = getComponentOf(e) as Position
        val rect = getComponentOf(e) as CollisionRect
        val collisions = sorted.filter { o ->
            val opos = getComponentOf(o) as Position
            val orect = getComponentOf(o) as CollisionRect
            var result = true

            result
        }
        collisions.size > 1
    }
})