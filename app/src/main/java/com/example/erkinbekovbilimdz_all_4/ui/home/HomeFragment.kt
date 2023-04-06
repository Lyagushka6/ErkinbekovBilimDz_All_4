package com.example.erkinbekovbilimdz_all_4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.erkinbekovbilimdz_all_4.App
import com.example.erkinbekovbilimdz_all_4.R
import com.example.erkinbekovbilimdz_all_4.databinding.FragmentHomeBinding
import com.example.erkinbekovbilimdz_all_4.model.Task
import com.example.erkinbekovbilimdz_all_4.ui.home.adapter.TaskAdapter
import com.example.erkinbekovbilimdz_all_4.ui.task.TaskFragment.Companion.TASK_KEY
import com.example.erkinbekovbilimdz_all_4.ui.task.TaskFragment.Companion.TASK_REQUEST

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter : TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this :: onLongClick, requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        binding.recyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun setData(){
        val data = App.db.TaskDao().getAll()
        adapter.addTasks(data)
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить?")
        alertDialog.setMessage("Вы точно хотите удалить?")
        alertDialog.setNegativeButton("Нет") { d, i ->
            d.dismiss()
        }
        alertDialog.setPositiveButton("Да") { d, i ->
            App.db.TaskDao().delete(task)
            d.dismiss()
            setData()
        }
        alertDialog.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}