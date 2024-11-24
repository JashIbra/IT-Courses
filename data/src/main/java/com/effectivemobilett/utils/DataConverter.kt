package com.effectivemobilett.utils

import org.joda.time.format.DateTimeFormat
import java.util.Locale

private val formatWithMillis = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
private val formatWithoutMillis = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
private const val targetFormatStr = "d MMMM yyyy"

fun getReadableDate(date: String, localeName: String): String {
    val parsedDate = try {
        formatWithMillis.parseDateTime(date)
    } catch (e: Exception) {
        formatWithoutMillis.parseDateTime(date)
    }
    val checkedLocale = if (isValidLocale(localeName)) Locale(localeName) else Locale.getDefault()
    return parsedDate.toString(targetFormatStr, checkedLocale)
}

fun isValidLocale(localeStr: String): Boolean {
    return try {
        val locale = Locale.forLanguageTag(localeStr)
        !locale.language.isNullOrEmpty()
    } catch (e: Exception) {
        false
    }
}
