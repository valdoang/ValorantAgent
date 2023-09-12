package com.valdoang.valorantagent.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.valdoang.valorantagent.R
import com.valdoang.valorantagent.core.domain.model.Valorant
import com.valdoang.valorantagent.databinding.ActivityDetailValorantBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailValorantActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailValorantViewModel: DetailValorantViewModel by viewModel()
    private lateinit var binding: ActivityDetailValorantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailValorantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailValorant = intent.getParcelableExtra<Valorant>(EXTRA_DATA)
        showDetailValorant(detailValorant)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetailValorant(detailValorant: Valorant?) {
        detailValorant?.let {
            supportActionBar?.title = detailValorant.agentName
            binding.tvDetailDesc.text = detailValorant.agentDescription
            Glide.with(this@DetailValorantActivity)
                .load(detailValorant.agentPortrait)
                .into(binding.ivDetailImage)

            var statusFavorite = detailValorant.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailValorantViewModel.setFavoriteTourism(detailValorant, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}