package id.walt.vclib.adapter

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

@Target(AnnotationTarget.FIELD)
annotation class ListOrSingleValue

@Target(AnnotationTarget.FIELD)
annotation class ListOrReadSingleValue


class ListOrSingleValueConverter(val singleReadOnly: Boolean = false): Converter {
  override fun canConvert(cls: Class<*>) = cls == List::class.java

  override fun fromJson(jv: JsonValue) =
    if (jv.array == null) {
      listOf(jv.inside)
    } else {
      jv.array
    }

  override fun toJson(value: Any): String {
    if((value as List<*>).size == 1 && !singleReadOnly) {
      return Klaxon().toJsonString(value.first())
    } else {
      return Klaxon().toJsonString(value)
    }
  }
}
