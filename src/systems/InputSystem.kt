package systems

import components.Input
import components.Velocity
import getComponentOf

public object InputSystem: System(listOf(Input::class, Velocity::class), run = { entities ->
    for (e in entities) {
        val vel = getComponentOf(e) as Velocity
        var ydir = 0.0
        when {
            Keystates.up -> ydir = 1.0
            Keystates.down -> ydir = -1.0
        }
        var xdir = 0.0
        when {
            Keystates.left -> xdir = -1.0
            Keystates.right -> xdir = 1.0
        }
        
    }
})
