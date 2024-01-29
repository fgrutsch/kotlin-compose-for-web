package com.fgrutsch.demo.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.fgrutsch.demo.decompose.RootComponent.Child.DetailsChild
import com.fgrutsch.demo.decompose.RootComponent.Child.ListChild
import kotlinx.serialization.Serializable

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    // It's possible to pop multiple screens at a time on iOS
    fun onBackClicked(toIndex: Int)

    // Defines all possible child components
    sealed class Child {
        class ListChild(val component: ListComponent) : Child()
        class DetailsChild(val component: DetailsComponent) : Child()
    }

}

class DefaultRootComponent(
    componentContext: ComponentContext,
    webHistoryController: WebHistoryController
) : RootComponent, ComponentContext by componentContext {


    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { initialStack(webHistoryController.historyPaths) }, // The initial stack is just the initial configuration
        handleBackButton = true, // Automatically pop from the stack on back button presses
        childFactory = ::child,
    )
    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack

    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            getPath = {
                when (it) {
                    is Config.List -> "#/list"
                    is Config.Details -> "#/detail/${it.itemId}"
                }
            },
            getConfiguration = {
                if (it == "/" || it == "#/list") Config.List
                else if (it.startsWith("#/detail/")) Config.Details(it.substringAfterLast("/"))
                else error("Unknown path: $it")
            },
        )
    }


    private fun initialStack(webHistoryPaths: List<String>?): List<Config> {
        return if (webHistoryPaths.isNullOrEmpty()) {
            listOf(Config.List)
        } else {
            val first = webHistoryPaths.first()
            if (first == "#/list") listOf(Config.List)
            else if (first.startsWith("#/detail")) listOf(Config.List, Config.Details("Detail"))
            else error("Unknown path: $first")
        }
    }


    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.List -> ListChild(listComponent(componentContext))
            is Config.Details -> DetailsChild(detailsComponent(componentContext, config))
        }

    private fun listComponent(componentContext: ComponentContext): ListComponent =
        DefaultListComponent(
            componentContext = componentContext,
            onItemSelected = { item: String -> // Supply dependencies and callbacks
                navigation.push(Config.Details(itemId = item)) // Push the details component
            },
        )

    private fun detailsComponent(componentContext: ComponentContext, config: Config.Details): DetailsComponent =
        DetailsComponent(
            componentContext = componentContext,
            itemId = config.itemId,
        )


    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Serializable // kotlinx-serialization plugin must be applied
    private sealed interface Config {
        @Serializable
        data object List : Config

        @Serializable
        data class Details(val itemId: String) : Config
    }
}


interface ListComponent {
    val model: Value<Model>

    fun onItemClicked(item: String)

    data class Model(
        val items: List<String>,
    )
}

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (item: String) -> Unit,
) : ListComponent {
    override val model: Value<ListComponent.Model> =
        MutableValue(ListComponent.Model(items = List(10) { it.toString() }))

    override fun onItemClicked(item: String) {
        onItemSelected(item)
    }
}

class DetailsComponent(
    componentContext: ComponentContext,
    private val itemId: String,
) {
    val model: Value<Model> =
        MutableValue(Model(item = itemId))

    data class Model(
        val item: String,
    )
}
