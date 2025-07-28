package com.example.calmly.data


import com.example.calmly.R

object SoundRepository {

    val meditationSounds = listOf(
        Sound(
            id = 1,
            name = "Forest",
            imageRes = R.drawable.forast_co_1,
            soundRes = R.raw.forest_rain_loop_1,
            category = SoundCategory.MEDITATION
        ),
        Sound(
            id = 2,
            name = "Rain",
            imageRes = R.drawable.rain_co_1,
            soundRes = R.raw.rain_loop_1,
            category = SoundCategory.MEDITATION
        ),
        Sound(
            id = 3,
            name = "Campfire",
            imageRes = R.drawable.campfire_cha_1,
            soundRes = R.raw.campfire_crackles_2,
            category = SoundCategory.MEDITATION
        ),
        Sound(
            id = 4,
            name = "Ocean",
            imageRes = R.drawable.ocean_cha_1,
            soundRes = R.raw.ocean_loop_1,
            category = SoundCategory.MEDITATION
        )
    )

    val sleepSounds = listOf(
        Sound(
            id = 5,
            name = "White Noise",
            imageRes = R.drawable.white_noice_cha_1,
            soundRes = R.raw.white_sound_1,
            category = SoundCategory.SLEEP
        ),
        Sound(
            id = 6,
            name = "Lullaby",
            imageRes = R.drawable.lullaby_cha_1,
            soundRes = R.raw.lullaby_baby_toy_music_box_1,
            category = SoundCategory.SLEEP
        ),
        Sound(
            id = 7,
            name = "Fan",
            imageRes = R.drawable.fan_co_2,
            soundRes = R.raw.fun_stove_extractor_1,
            category = SoundCategory.SLEEP
        ),
        Sound(
            id = 8,
            name = "Deep Hum",
            imageRes = R.drawable.deep_hun_cha_1,
            soundRes = R.raw.deep_hum_brass_hum_1,
            category = SoundCategory.SLEEP
        )
    )

    val popularOnCalmly=listOf(
        Sound(
            id = 6,
            name = "Lullaby",
            imageRes = R.drawable.lullaby_cha_1,
            soundRes = R.raw.lullaby_baby_toy_music_box_1,
            category = SoundCategory.SLEEP
        ),
        Sound(
            id = 7,
            name = "Fan",
            imageRes = R.drawable.fan_co_2,
            soundRes = R.raw.fun_stove_extractor_1,
            category = SoundCategory.SLEEP
        ),
        Sound(
            id = 8,
            name = "Deep Hum",
            imageRes = R.drawable.deep_hun_cha_1,
            soundRes = R.raw.deep_hum_brass_hum_1,
            category = SoundCategory.SLEEP
        ),
        Sound(
            id = 2,
            name = "Rain",
            imageRes = R.drawable.rain_co_1,
            soundRes = R.raw.rain_loop_1,
            category = SoundCategory.MEDITATION
        ),
        Sound(
            id = 3,
            name = "Campfire",
            imageRes = R.drawable.campfire_cha_1,
            soundRes = R.raw.campfire_crackles_2,
            category = SoundCategory.MEDITATION
        ),
        Sound(
            id = 4,
            name = "Ocean",
            imageRes = R.drawable.ocean_cha_1,
            soundRes = R.raw.ocean_loop_1,
            category = SoundCategory.MEDITATION
        )
    )
}