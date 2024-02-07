/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * LangPrefs.kt is part of Kizzy
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.kizzy.preference

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import com.my.kizzy.resources.R

// Languages
enum class LANGUAGES
{
    SYSTEM_DEFAULT,
    ENGLISH,
    TURKISH,
    DUTCH,
    RUSSIAN,
    POLISH,
    PORTUGUESE,
    INDONESIAN,
    SIMPLIFIED_CHINESE,
    BURMESE,
    VIETNAMESE,
    ITALIAN,
    FILIPINO,
    FRENCH,
    CROATIAN,
    FARSI
}

val languages: Map<Int, String> =
    mapOf(
        Pair(LANGUAGES.ENGLISH.ordinal, "en"),
        Pair(LANGUAGES.TURKISH.ordinal, "tr"),
        Pair(LANGUAGES.DUTCH.ordinal,"nl"),
        Pair(LANGUAGES.RUSSIAN.ordinal, "ru"),
        Pair(LANGUAGES.POLISH.ordinal, "pl"),
        Pair(LANGUAGES.PORTUGUESE.ordinal, "pt"),
        Pair(LANGUAGES.INDONESIAN.ordinal, "in"),
        Pair(LANGUAGES.SIMPLIFIED_CHINESE.ordinal, "zh"),
        Pair(LANGUAGES.BURMESE.ordinal, "mm"),
        Pair(LANGUAGES.VIETNAMESE.ordinal, "vi"),
        Pair(LANGUAGES.ITALIAN.ordinal, "it"),
        Pair(LANGUAGES.FILIPINO.ordinal, "fil"),
        Pair(LANGUAGES.FRENCH.ordinal, "fr"),
        Pair(LANGUAGES.CROATIAN.ordinal,"hr"),
        Pair(LANGUAGES.FARSI.ordinal, "fa")
    )

fun getLanguageConfig(languageNumber: Int = Prefs[Prefs.LANGUAGE]): String {
    return if (languages.containsKey(languageNumber)) languages[languageNumber].toString() else ""
}

private fun getLanguageNumberByCode(languageCode: String): Int {
    languages.entries.forEach {
        if (it.value == languageCode) return it.key
    }
    return LANGUAGES.SYSTEM_DEFAULT.ordinal
}

fun getLanguageNumber(): Int {
    return if (Build.VERSION.SDK_INT >= 33)
        getLanguageNumberByCode(
            LocaleListCompat.getAdjustedDefault()[0]?.toLanguageTag().toString()
        )
    else Prefs[Prefs.LANGUAGE, LANGUAGES.SYSTEM_DEFAULT.ordinal]
}

@Composable
fun getLanguageDesc(language: Int = getLanguageNumber()): String {
    return stringResource(
        when (language) {
            LANGUAGES.SIMPLIFIED_CHINESE.ordinal -> R.string.locale_zh
            LANGUAGES.ENGLISH.ordinal -> R.string.locale_en
            LANGUAGES.TURKISH.ordinal -> R.string.locale_tr
            LANGUAGES.RUSSIAN.ordinal -> R.string.locale_ru
            LANGUAGES.INDONESIAN.ordinal -> R.string.locale_in
            LANGUAGES.DUTCH.ordinal -> R.string.locale_nl
            LANGUAGES.POLISH.ordinal -> R.string.locale_pl
            LANGUAGES.PORTUGUESE.ordinal -> R.string.locale_pt
            LANGUAGES.BURMESE.ordinal -> R.string.locale_mm
            LANGUAGES.VIETNAMESE.ordinal -> R.string.locale_vi
            LANGUAGES.ITALIAN.ordinal -> R.string.locale_it
            LANGUAGES.FILIPINO.ordinal -> R.string.locale_fil
            LANGUAGES.FRENCH.ordinal -> R.string.locale_fr
            LANGUAGES.CROATIAN.ordinal -> R.string.locale_hr
            LANGUAGES.FARSI.ordinal -> R.string.locale_fa
            else -> R.string.follow_system
        }
    )
}
