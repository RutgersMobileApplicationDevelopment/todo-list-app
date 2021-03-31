package club.rumad.todolistapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("todos")
fun RecyclerView.setTodos(todos: MutableList<Todo>?) {
    if (todos != null) {
        val todosAdapter = adapter as TodosAdapter
        todosAdapter.submitList(todos)
    }
}
