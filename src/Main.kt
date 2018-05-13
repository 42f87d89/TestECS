import components.Position
import components.Velocity
import components.Visual
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWKeyCallbackI
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import resources.Image
import systems.Physics
import systems.Render

var window: Long = 0

fun main(args: Array<String>) {
    initWindow(800, 500, "ECS!")
    registerSystem(Physics)
    registerSystem(Render)
    registerComponent(Position::class)
    registerComponent(Velocity::class)
    registerComponent(Visual::class)
    val p = Position(x = 0.0, y = 0.0, Id = 0)
    val v = Velocity(x = 1.0, y = 0.0, Id = 0)
    val vis = Visual(0, Image(0.5f,Triple(0.0f,0.5f,0.0f)))
    val p2 = Position(x = 0.0, y = 0.0, Id = 0)
    val v2 = Velocity(x = 0.0, y = 1.0, Id = 0)
    val vis2 = Visual(0, Image(0.5f,Triple(0.5f,0.0f,0.0f)))
    addComponentToEntity(0, v)
    addComponentToEntity(0, p)
    addComponentToEntity(0, vis)
    addComponentToEntity(1, v2)
    addComponentToEntity(1, p2)
    addComponentToEntity(1, vis2)
    while (!GLFW.glfwWindowShouldClose(window)) {
        val startTime = GLFW.glfwGetTime()
        GLFW.glfwPollEvents()

        runSystems()
        val period = GLFW.glfwGetTime()-startTime
        if (period*1000<1000.0/60) Thread.sleep((1000.0/60-period*1000).toLong())
    }

}

fun initWindow(width: Int, height: Int, title: String) {

    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (!GLFW.glfwInit()) {
        throw IllegalStateException("Unable to initialize GLFW")
    }

    // Create the window
    window = GLFW.glfwCreateWindow(width, height, title as CharSequence, 0, 0)
    GLFW.glfwMakeContextCurrent(window)
    GLFW.glfwShowWindow(window)
    GL.createCapabilities()
    val w = width/height.toDouble()
    GL11.glOrtho(-w,w,-1.0,1.0,1.0,-1.0)
    GLFW.glfwSetWindowSizeCallback(window) { _, w, h ->
        val w = w/h.toDouble()
        GL11.glOrtho(-w,w,-1.0,1.0,1.0,-1.0)
    }

    GLFW.glfwSetKeyCallback(window) { _, key, _, action, modifiers ->

    }
}