package com.example.nasaneo.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.nasaneo.databinding.ListFragmentBinding
import com.example.nasaneo.di.AppComponent
import com.example.nasaneo.di.getComponent
import com.example.nasaneo.di.viewModels

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels {
        getComponent<AppComponent>()
            .plus(ListModule())
            .viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.currentState.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val actionListFragmentToDetailsFragment =
                    ListFragmentDirections.actionListFragmentToDetailsFragment(it.neo)
                findNavController().navigate(actionListFragmentToDetailsFragment)
            }
        }
    }
}

// startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(...)))
//
