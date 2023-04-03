package com.example.erkinbekovbilimdz_all_4.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.window.OnBackInvokedCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.erkinbekovbilimdz_all_4.databinding.ItemOnboardingBinding
import com.example.erkinbekovbilimdz_all_4.model.OnBoard
import com.example.erkinbekovbilimdz_all_4.utils.loadImage

class OnBoardingAdapter(private val onClick:() -> Unit,
                        private val onNextClick:() -> Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf<OnBoard>(
        OnBoard("https://media.istockphoto.com/id/1362675393/zh/%E5%90%91%E9%87%8F/tiny-male-character-carry-huge-burger-isolated-on-white-background-man-enjoying-fast-food.jpg?s=612x612&w=0&k=20&c=v-QJ2DgmTclAEpMoLOSbYymARMljqMWBc_yv7A8iPdU=", "Food", "Nutrition is one of the most significant factors in life, and it's hard to argue with that."),
        OnBoard("https://media.istockphoto.com/id/1318913887/vector/man-reading-book-character-illustration.jpg?s=612x612&w=0&k=20&c=EBjvpUNwuBa7apfLIfVOrKmQU4r_0chSKs2d-zK6_3Q=", "Dream", "One of the basic human needs, just like sleeping or eating."),
        OnBoard("https://img.freepik.com/premium-vector/completed-task-work-done-happy-woman-worker-business-secretary-cartoon-illustration_385073-97.jpg?w=2000", "Work", "Allows us to feel a sense of belonging to something bigger than ourselves: a profession, a team, a mission of national importance, or research that will change the future.")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
             false
           )
        )
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class OnBoardingViewHolder(private val binding:ItemOnboardingBinding):
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.description
            binding.imgBound.loadImage(onBoard.image)
            binding.btnSkip.isVisible = adapterPosition != 2
            binding.bntStart.isVisible = adapterPosition == 2
            binding.btnNext.isVisible = adapterPosition !=2
            binding.btnSkip.setOnClickListener {
                onClick()
            }
            binding.bntStart.setOnClickListener {
                onClick()
            }
            binding.btnNext.setOnClickListener {
                onNextClick()
            }
        }
    }
}