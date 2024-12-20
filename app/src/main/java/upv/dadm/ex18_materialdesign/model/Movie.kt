/*
 * Copyright (c) 2022-2024 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex18_materialdesign.model

/**
 * A movie object consisting of its id, title, overview, website link, and list of genres.
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val link: String,
    val genres: List<Int>
)
