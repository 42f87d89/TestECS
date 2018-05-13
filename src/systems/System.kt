package systems

import components.Component
import kotlin.reflect.KClass

public open abstract class System(open val components: List<KClass<out Component>>, val run: (List<Int>)->Unit)