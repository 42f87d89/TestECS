package components

data class CollisionRect(override var Id: Int, val Width: Float, val Height: Float, val callback: (Int,Int)->Unit): Component
