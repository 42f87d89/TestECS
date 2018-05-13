package systems

import components.Position
import components.Visual
import getComponentOf
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import window

private fun paintSquare(x: Float, y: Float, w: Float, color: Triple<Float,Float,Float>) {
    GL11.glBegin(GL11.GL_QUADS)
    GL11.glColor3f(color.first,color.second,color.third)
    GL11.glVertex2f(x, y)
    GL11.glVertex2f(x, y + w)
    GL11.glVertex2f(x + w, y + w)
    GL11.glVertex2f(x + w, y)
    GL11.glEnd()
}
public object Render: System(listOf(Position::class, Visual::class), run = { entities ->
    GL11.glClearColor(0.0f,0.0f,0.0f,1.0f)
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
    for (e in entities) {
        val pos = getComponentOf(e, Position::class) as Position
        val img = getComponentOf(e, Visual::class) as Visual
        paintSquare((pos.x/1600.0).toFloat(), (pos.y/1600.0).toFloat(), img.image.width, img.image.color)
    }
    GLFW.glfwSwapBuffers(window)
})
