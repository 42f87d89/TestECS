import components.Component
import systems.System
import kotlin.reflect.KClass

private var systems: ArrayList<System> = ArrayList(10)
private var currentId = 0

var components  = ComponentMap()

public fun registerSystem(sys: System) {
    systems.add(sys)
}

public fun registerComponent(c: KClass<out Component>) {
    components[c] = arrayListOf()
}

public fun addComponentToEntity(id: Int, c: Component) {
    c.Id = id
    if (!components.containsKey(c::class)) registerComponent(c::class)
    components[c::class]?.add(c)
}

public fun getEntitiesWithComponents(compTypes: List<KClass<out Component>>): List<Int> {
    val sorted = compTypes.sortedBy { comp-> components[comp]?.size ?: 0 }
    var result = components[sorted[0]]?.map { x -> x.Id } ?: listOf()
    for (ct in sorted) {
        val ids = components[ct]?.map { x -> x.Id } ?: listOf()
        result = result.intersect(ids).toList()
    }
    return result
}

public inline fun <reified T: Component>getComponentOf(id: Int): T {
    return components[T::class]?.find { x -> x.Id == id }!! as T
}

public fun runSystems() {
    for (s in systems) {
        s.run(getEntitiesWithComponents(s.components))
    }
}