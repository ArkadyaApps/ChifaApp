package com.arkadya.chifa.forum.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.semantics.text
import androidx.compose.ui.unit.plus
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.lifecycle.viewmodel.CreationExtras
import com.arkadya.chifa.databinding.FragmentForumBinding
import com.arkadya.chifa.forum.viewmodels.ForumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForumFragment : Fragment() {
    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ForumViewModel by viewModels {
        val roomName = "General Discussion"
        val extras = CreationExtras.Empty.plus(ForumViewModel.ROOM_NAME_KEY to roomName)
        ForumViewModel.Factory.create(extras)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe the messages
        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            // Update the UI with the messages
            // For example, update a RecyclerView adapter
        }

        // Handle sending messages
        binding.sendMessageButton.setOnClickListener {
            viewModel.sendMessage()
        }

        // Handle updating the message text
        binding.messageEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                viewModel.updateMessageText(binding.messageEditText.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}