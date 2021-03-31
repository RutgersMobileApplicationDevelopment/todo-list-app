package club.rumad.todolistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TodosViewModel : ViewModel() {
    private val _todos = MutableLiveData<MutableList<Todo>>(mutableListOf())
    val todos: LiveData<MutableList<Todo>>
        get() = _todos

    fun addTodo() {
        // TODO: Provide a way for the user to specify the text for the new to-do item
        val newTodo = Todo(todos.value!!.size, Random.nextInt().toString())
        _todos.value = todos.value!!.plus(newTodo).toMutableList()
    }

    fun removeTodo(position: Int) {
        todos.value!!.removeAt(position)
    }
}
