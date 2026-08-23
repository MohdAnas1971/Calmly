package com.calmly_example.calmly.data

import com.calmly_example.calmly.R

object SoundRepository {

    // ✅ One common list
    private val allSounds = listOf(
        Sound(1, "Forest", R.drawable.forast_co_1, R.raw.forest_loop, SoundCategory.MEDITATION),
        Sound(2, "Rain", R.drawable.rain_co_1, R.raw.rain_loop_3, SoundCategory.MEDITATION),
        Sound(3, "Campfire", R.drawable.campfire_cha_1, R.raw.campfire_crackles_2, SoundCategory.MEDITATION),
        Sound(4, "Ocean", R.drawable.ocean_cha_1, R.raw.ocean_loop_1, SoundCategory.MEDITATION),

        Sound(5, "White Noise", R.drawable.white_noice_cha_1, R.raw.white_sound_1, SoundCategory.SLEEP),
        Sound(6, "Lullaby", R.drawable.lullaby_cha_1, R.raw.lullaby_baby_toy_music_box_1, SoundCategory.SLEEP),
        Sound(7, "Fan", R.drawable.fan_co_2, R.raw.fun_stove_extractor_1, SoundCategory.SLEEP),
        Sound(8, "Deep Hum", R.drawable.deep_hun_cha_1, R.raw.deep_hum_brass_hum_1, SoundCategory.SLEEP)
    )
    // ✅ Extract by category
    val meditationSounds = allSounds.filter {
        it.category == SoundCategory.MEDITATION
    }
    val sleepSounds = allSounds.filter {
        it.category == SoundCategory.SLEEP
    }
    // ✅ Popular (by ID)
    val popularOnCalmly = allSounds.filter { it.id in setOf(6,7,8,2,3,4) }
}

