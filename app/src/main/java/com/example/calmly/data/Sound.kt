package com.example.calmly.data



data class Sound(
    val id: Int,
    val name: String,
    val imageRes: Int,  // Drawable resource ID
    val soundRes: Int,  // Raw audio resource ID
    val category: SoundCategory
)


enum class SoundCategory {
    MEDITATION,
    SLEEP
}
