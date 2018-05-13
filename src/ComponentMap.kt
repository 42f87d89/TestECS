import components.Component
import kotlin.reflect.KClass

public class ComponentMap(): HashMap<KClass<out Component>, ArrayList<Component>>() {
    companion object: HashMap<KClass<out Component>, ArrayList<Component>>() {
        override fun get(key: KClass<out Component>): java.util.ArrayList<Component>? {
            if (Companion[key] == null) println("${Companion[key]} is not registered")
            return super.get(key)
        }
    }

}