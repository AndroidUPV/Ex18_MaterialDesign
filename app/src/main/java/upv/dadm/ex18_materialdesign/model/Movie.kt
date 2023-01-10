/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex18_materialdesign.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val link: String,
    val genres: List<Int>
)
