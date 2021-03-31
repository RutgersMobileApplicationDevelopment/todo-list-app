package club.rumad.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import club.rumad.todolistapp.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    val viewModel by activityViewModels<TodosViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = TodosAdapter()
        binding.recyclerViewTodos.adapter = adapter

        // implementing swipe-to-remove
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeTodo(viewHolder.adapterPosition)
                (binding.recyclerViewTodos.adapter as TodosAdapter).notifyItemRemoved(viewHolder.adapterPosition)
                Snackbar.make(binding.frameLayout, "Todo removed", Snackbar.LENGTH_LONG).show()
            }

        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewTodos)


        return binding.root
    }

}
