
package com.arkadya.chifa.data
import com.arkadya.chifa.R
data class Language(
    val code: String,
    val displayName: String,
    val flagResId: Int
)


val supportedLanguages = listOf(
    Language("en", "English", R.drawable.ic_flag_en),
    Language("ar", "العربية", R.drawable.ic_flag_ar),
    Language("fr", "Français", R.drawable.ic_flag_fr)
)