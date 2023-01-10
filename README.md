# Ex18_MaterialDesign
Lecture 02 - Development of Graphical User Interfaces (GUI)

The app displays a list of 20 movies currently in theatres (Christmas 2022).
- A single ViewModel is shared between the Fragments to access access all information about items to display.
- A custom (and themed) ToolBar is set up as the ActionBar of the app. It is enclosed within an AppBarLayout, which is also within a CoordinatorLayout, to enable responding to scrolling events from the RecyclerView.
- A standard (permanent) BottomSheet enables filtering the list of movies according to selected genres (Chips within a ChipGroup). A Snackbar displays a message when no movie matches the selected genres (it can be swiped to dismiss it as it is also enclosed within a CoordinatorLayout).
- The details of the movie (cover, overview) are displayed upon clicking on any item in the RecyclerView. A FloatingActionButton gives access the movie's official website. Another FloatingActionButton displays a form (a modal BottomSheet) to add a comment to the movie (it is fake, it actually does nothing).
