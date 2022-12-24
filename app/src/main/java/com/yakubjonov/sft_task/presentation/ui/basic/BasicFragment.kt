package com.yakubjonov.sft_task.presentation.ui.basic

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yakubjonov.domain.common.Resource
import com.yakubjonov.domain.model.Card
import com.yakubjonov.sft_task.App
import com.yakubjonov.sft_task.core.extensions.closeKeyboard
import com.yakubjonov.sft_task.databinding.FragmentBasicBinding
import com.yakubjonov.sft_task.presentation.SftView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasicFragment : Fragment() {

    private var _binding: FragmentBasicBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : BasicViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = App.appComponent.factory.create(BasicViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeGetCardState()
    }

    private fun observeGetCardState() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.card.collect{ state ->
                when(state){
                    is Resource.Loading -> {
                        (activity as SftView?)?.dialogShow()
                        binding.cardDetails.root.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        (activity as SftView?)?.dialogHide()
                        setCard(state.data!!)
                    }
                    is Resource.Error -> {
                        (activity as SftView?)?.dialogShowError(state.messageId!!)
                        binding.cardDetails.root.visibility = View.GONE
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCard(card: Card) {
        binding.cardDetails.apply {
            root.visibility = View.VISIBLE

            schemeLayout.titleTv.text = "SCHEME / NETWORK"
            val scheme = if (card.scheme!=null) card.scheme?.ifEmpty { "-" } else "-"
            schemeLayout.contentTv.text = scheme

            brandLayout.titleTv.text = "BRAND"
            val brand = if (card.brand!=null) card.brand!!.ifEmpty { "-" } else "-"
            brandLayout.contentTv.text = brand

            numberLayout.titleTv.text = "CARD NUMBER"
            val length = "length: " + (if (card.number!=null) card.number?.length.toString().ifEmpty { "-" } else "-")
            numberLayout.contentTv.text = length

            typeLayout.titleTv.text = "TYPE"
            val type = if (card.type!=null) card.type else "-"
            typeLayout.contentTv.text = type

            prepaidLayout.titleTv.text = "PREPAID"
            prepaidLayout.contentTv.text = if (card.prepaid == true) "YES" else "NO"

            countryLayout.titleTv.text = "COUNTRY"
            val countryName = if (card.country!=null) card.country?.name?.ifEmpty { "-" } else "-"
            countryLayout.contentTv.text = countryName

            bankLayout.titleTv.text = "BANK"
            val bankName = if (card.bank!=null) card.bank?.name?.ifEmpty { "-" } else "-"
            bankLayout.contentTv.text = bankName
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupUI() {
        binding.apply {

            // Для очистить BinEditText
            binEt.setOnTouchListener { view, motionEvent ->
                val right = 2
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    if (motionEvent.rawX >= binEt.right - binEt.compoundDrawables[right].bounds.width()) {
                        binEt.text.clear()
                        true
                    }
                }
                false
            }

            // Проверить BIN
            binEt.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        binding.searchBtn.isEnabled = s.toString().length==8
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            // Для найти BIN
            searchBtn.setOnClickListener {
                binding.root.closeKeyboard(requireActivity())
                viewModel.getCard(binEt.text.toString().toInt())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}