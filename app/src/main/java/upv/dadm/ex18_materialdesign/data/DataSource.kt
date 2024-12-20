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

package upv.dadm.ex18_materialdesign.data

import upv.dadm.ex18_materialdesign.model.Genre
import upv.dadm.ex18_materialdesign.model.Movie

/**
 * Provides data for movies and genres.
 */
object DataSource {

    /**
     * Returns the full list of movies in theatres.
     */
    fun getMovies() = listOf(
        Movie(
            76600,
            "Avatar: The Way of Water",
            "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.\n",
            "https://www.avatar.com/movies/avatar-the-way-of-water",
            listOf(878, 12, 28)
        ),
        Movie(
            436270,
            "Black Adam",
            "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            "https://www.dc.com/BlackAdam",
            listOf(28, 14, 878)
        ),
        Movie(
            899112,
            "Violent Night",
            "When a team of mercenaries breaks into a wealthy family compound on Christmas Eve, taking everyone inside hostage, the team isn’t prepared for a surprise combatant: Santa Claus is on the grounds, and he’s about to show why this Nick is no saint.",
            "https://www.violentnightmovie.com",
            listOf(28, 35, 80, 53)
        ),
        Movie(
            555604,
            "Guillermo del Toro's Pinocchio",
            "During the rise of fascism in Mussolini's Italy, a wooden boy brought magically to life struggles to live up to his father's expectations.",
            "https://www.netflix.com/title/80218455",
            listOf(16, 14, 18)
        ),
        Movie(
            724495,
            "The Woman King",
            "The story of the Agojie, the all-female unit of warriors who protected the African Kingdom of Dahomey in the 1800s with skills and a fierceness unlike anything the world has ever seen, and General Nanisca as she trains the next generation of recruits and readies them for battle against an enemy determined to destroy their way of life.",
            "https://www.thewomanking.movie/",
            listOf(28, 18, 36)
        ),
        Movie(
            676547,
            "Prey for the Devil",
            "In response to a global rise in demonic possessions, the Catholic Church reopens exorcism schools to train priests in the Rite of Exorcism. On this spiritual battlefield, an unlikely warrior rises: a young nun, Sister Ann. Thrust onto the spiritual frontline with fellow student Father Dante, Sister Ann finds herself in a battle for the soul of a young girl and soon realizes the Devil has her right where he wants her.",
            "https://www.preyforthedevil.movie/",
            listOf(27, 53)
        ),
        Movie(
            740952,
            "Savage Salvation",
            "Newly engaged Shelby John and Ruby Red want a fresh start after their struggles with addiction, but when Shelby discovers his beloved Ruby dead on their porch, he embarks on a vengeful killing spree of the dealers who supplied her. Armed with nothing but adrenaline and a nail gun, Shelby begins to unleash chaos on the town’s criminal underbelly, as he hunt’s down crime lord Coyote. Sheriff Church must race against the clock to put an end to Shelby's vigilante justice before the entire town descends into a bloodbath.",
            "https://theavenue.film/movies/savage-salvation",
            listOf(28, 53, 80, 18)
        ),
        Movie(
            683328,
            "The Big 4",
            "A by-the-book female detective teams up with four down-on-their-luck assassins to investigate her father's murder.",
            "https://www.netflix.com/title/81344253",
            listOf(28, 35, 80)
        ),
        Movie(
            505642,
            "Black Panther: Wakanda Forever",
            "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death. As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.",
            "https://wakandaforevertickets.com",
            listOf(28, 12, 878)
        ),
        Movie(
            1024546,
            "Detective Knight: Rogue",
            "As Los Angeles prepares for Halloween, mask-wearing armed robbers critically wound detective James Knight’s partner in a shootout following a heist. With Knight in hot pursuit, the bandits flee L.A. for New York, where the detective’s dark past collides with his present case and threatens to tear his world apart…unless redemption can claim Knight first.",
            "https://www.lionsgate.com/movies/detective-knight-rogue",
            listOf(28, 80)
        ),
        Movie(
            19995,
            "Avatar",
            "In the 22nd century, a paraplegic Marine is dispatched to the moon Pandora on a unique mission, but becomes torn between following orders and protecting an alien civilization.",
            "https://www.avatar.com/movies/avatar",
            listOf(28, 12, 14, 878)
        ),
        Movie(
            1013860,
            "R.I.P.D. 2: Rise of the Damned",
            "When Sheriff Roy Pulsipher finds himself in the afterlife, he joins a special police force and returns to Earth to save humanity from the undead.",
            "",
            listOf(14, 28, 35, 80)
        ),
        Movie(
            315162,
            "Puss in Boots: The Last Wish",
            "Puss in Boots discovers that his passion for adventure has taken its toll: He has burned through eight of his nine lives, leaving him with only one life left. Puss sets out on an epic journey to find the mythical Last Wish and restore his nine lives.",
            "https://www.pussinbootsmovie.ca",
            listOf(16, 12, 35, 10751, 14)
        ),
        Movie(
            551271,
            "Medieval",
            "The story of 14th century Czech icon and warlord Jan Zizka who defeated armies of the Teutonic Order and the Holy Roman Empire.",
            "https://theavenue.film/movies/medieval",
            listOf(36, 28, 18)
        ),
        Movie(
            972313,
            "Blowback",
            "When a master thief is sabotaged during a bank heist and left for dead, he seeks revenge on his former crew one target at a time. Now, with the cops and the mob closing in , he's in the race of his life to reclaim an untold fortune in cryptocurrency from those who double-crossed him.",
            "",
            listOf(28, 53, 80)
        ),
        Movie(
            880841,
            "Abandoned",
            "After a young couple moves into a remote farmhouse with their infant son, the woman's struggles with postpartum psychosis begin to intensify... as the house reveals secrets of its own.",
            "",
            listOf(27, 9648, 53)
        ),
        Movie(
            715931,
            "Emancipation",
            "Inspired by the gripping true story of a man who would do anything for his family—and for freedom. When Peter, an enslaved man, risks his life to escape and return to his family, he embarks on a perilous journey of love and endurance.",
            "https://tv.apple.com/movie/umc.cmc.1j6fdxookwtqml3bd8ivvcbbv",
            listOf(36, 18, 53, 28)
        ),
        Movie(
            663712,
            "Terrifier 2",
            "After being resurrected by a sinister entity, Art the Clown returns to Miles County where he must hunt down and destroy a teenage girl and her younger brother on Halloween night.  As the body count rises, the siblings fight to stay alive while uncovering the true nature of Art's evil intent.",
            "http://www.terrifier2themovie.com/",
            listOf(27, 53)
        ),
        Movie(
            361743,
            "Top Gun: Maverick",
            "After more than thirty years of service as one of the Navy’s top aviators, and dodging the advancement in rank that would ground him, Pete “Maverick” Mitchell finds himself training a detachment of TOP GUN graduates for a specialized mission the likes of which no living pilot has ever seen.",
            "https://www.topgunmovie.com",
            listOf(28, 18)
        ),
        Movie(
            1001865,
            "Scrooge: A Christmas Carol",
            "On a cold Christmas Eve, selfish miser Ebenezer Scrooge has one night left to face his past — and change the future — before time runs out .",
            "https://www.netflix.com/title/81028225",
            listOf(16, 10751, 14, 18)
        )
    )

    /**
     * Returns the full list of movie genres.
     */
    fun getGenresList() = listOf(
        Genre(12, "Adventure"),
        Genre(14, "Fantasy"),
        Genre(16, "Animation"),
        Genre(18, "Drama"),
        Genre(27, "Horror"),
        Genre(28, "Action"),
        Genre(35, "Comedy"),
        Genre(36, "History"),
        Genre(53, "Thriller"),
        Genre(80, "Crime"),
        Genre(878, "Science Fiction"),
        Genre(9648, "Mystery"),
        Genre(10751, "Family")
    )

    /**
     * Returns the list of movies that belong to any of the provided genres.
     */
    fun filterMoviesByGenre(genresList: List<Int>): List<Movie> =
        getMovies().filter { movie ->
            movie.genres.any { genre -> genre in genresList }
        }


}
