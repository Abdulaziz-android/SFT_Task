package com.yakubjonov.sft_task.presentation.ui.history

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yakubjonov.domain.common.Resource
import com.yakubjonov.domain.model.Card
import com.yakubjonov.sft_task.App
import com.yakubjonov.sft_task.R
import com.yakubjonov.sft_task.databinding.FragmentHistoryBinding
import com.yakubjonov.sft_task.databinding.ItemCardDetailsBinding
import com.yakubjonov.sft_task.presentation.SftView
import com.yakubjonov.sft_task.presentation.ui.adapter.CardAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HistoryFragment : Fragment(), CardAdapter.OnCardClickListener {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: CardAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = App.appComponent.factory.create(HistoryViewModel::class.java)
        adapter = CardAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
        Log.d(TAG, "onCreate: ")
    }

    private val TAG = "HistoryFragment"

    private fun observeData() {
        (activity as SftView?)?.dialogShow()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.cards.collect{ state ->
                when(state){
                    is Resource.Loading -> {
                        (activity as SftView?)?.dialogShow()
                        Log.d(TAG, "observeData: loading")
                    }
                    is Resource.Success -> {
                        delay(200)
                        Log.d(TAG, "observeData: success")
                        (activity as SftView?)?.dialogHide()
                        setData(state.data!!)
                    }
                    is Resource.Error -> {
                        (activity as SftView?)?.dialogShowError(state.messageId!!)
                    }
                }
            }
        }
    }

    private fun setData(data: List<Card>) {
        adapter.submitList(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: TT")
        binding.rv.adapter = adapter
        viewModel.getCards()
    }

    override fun onCardClicked(card: Card) {
        openCardDialog(card)
    }

    @SuppressLint("SetTextI18n")
    private fun openCardDialog(card: Card) {
        val dialog = AlertDialog.Builder(requireContext(), R.style.MyDialog).create()
        val itemBinding = ItemCardDetailsBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setView(itemBinding.root)
        itemBinding.apply {
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
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}