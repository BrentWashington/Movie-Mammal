package com.apps.washington.moviemammal.utils;

/**
 * Created by Brent on 3/5/2018.
 */

import android.net.Uri;
import android.util.Log;

import com.apps.washington.moviemammal.constructor.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Handles the JSON query data
 */
public final class QueryUtils {

    // TODO The poster won't display. Fix E/BitmapFactory: Unable to decode stream: java.io.FileNotFoundException

    /**
     * Log tag
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * The base URL for the movie poster
     *
     * The size is w185
     *
     * The value extracted from the key "poster_path" will be appended to this URL for
     * the movie poster
     */
    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    /**
     * Hardcoded JSON response for popular movies
     */
    private static final String POPULAR_MOVIES_URL = "{\"results\":[{\"vote_count\":4550,\"id\":284053,\"video\":false,\"vote_average\":7.4,\"title\":\"Thor: Ragnarok\",\"popularity\":367.075175,\"poster_path\":\"\\/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg\",\"original_language\":\"en\",\"original_title\":\"Thor: Ragnarok\",\"genre_ids\":[878,28,12,35,14],\"backdrop_path\":\"\\/5wNUJs23rT5rTBacNyf5h83AynM.jpg\",\"adult\":false,\"overview\":\"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.\",\"release_date\":\"2017-10-25\"},{\"vote_count\":2094,\"id\":284054,\"video\":false,\"vote_average\":7.4,\"title\":\"Black Panther\",\"popularity\":316.754014,\"poster_path\":\"\\/uxzzxijgPIY7slzFvMotPv8wjKA.jpg\",\"original_language\":\"en\",\"original_title\":\"Black Panther\",\"genre_ids\":[28,12,14,878],\"backdrop_path\":\"\\/b6ZJZHUdMEFECvGiDpJjlfUWela.jpg\",\"adult\":false,\"overview\":\"After the events of Captain America: Civil War, King T'Challa returns home to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne from factions within his own country. When two foes conspire to destroy Wakanda, the hero known as Black Panther must team up with C.I.A. agent Everett K. Ross and members of the Dora Milaje, Wakandan special forces, to prevent Wakanda from being dragged into a world war.\",\"release_date\":\"2018-02-13\"},{\"vote_count\":762,\"id\":337167,\"video\":false,\"vote_average\":6.3,\"title\":\"Fifty Shades Freed\",\"popularity\":285.720607,\"poster_path\":\"\\/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg\",\"original_language\":\"en\",\"original_title\":\"Fifty Shades Freed\",\"genre_ids\":[18,10749],\"backdrop_path\":\"\\/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg\",\"adult\":false,\"overview\":\"Believing they have left behind shadowy figures from their past, newlyweds Christian and Ana fully embrace an inextricable connection and shared life of luxury. But just as she steps into her role as Mrs. Grey and he relaxes into an unfamiliar stability, new threats could jeopardize their happy ending before it even begins.\",\"release_date\":\"2018-02-07\"},{\"vote_count\":1953,\"id\":399055,\"video\":false,\"vote_average\":7.5,\"title\":\"The Shape of Water\",\"popularity\":269.492546,\"poster_path\":\"\\/k4FwHlMhuRR5BISY2Gm2QZHlH5Q.jpg\",\"original_language\":\"en\",\"original_title\":\"The Shape of Water\",\"genre_ids\":[18,14,10749],\"backdrop_path\":\"\\/bAISaVhsaoyyQMZ55cpTwCdzek4.jpg\",\"adult\":false,\"overview\":\"An other-worldly story, set against the backdrop of Cold War era America circa 1962, where a mute janitor working at a lab falls in love with an amphibious man being held captive there and devises a plan to help him escape.\",\"release_date\":\"2017-12-01\"},{\"vote_count\":972,\"id\":391713,\"video\":false,\"vote_average\":7.7,\"title\":\"Lady Bird\",\"popularity\":199.88603,\"poster_path\":\"\\/vbvHTI7vfPKsONw75g9lio38SpN.jpg\",\"original_language\":\"en\",\"original_title\":\"Lady Bird\",\"genre_ids\":[35,18],\"backdrop_path\":\"\\/2ByWxoMbfE3pxztCJn5qTJ5Ui2Y.jpg\",\"adult\":false,\"overview\":\"A California high school student plans to escape from her family and small town by going to college in New York.\",\"release_date\":\"2017-09-08\"},{\"vote_count\":2804,\"id\":354912,\"video\":false,\"vote_average\":7.8,\"title\":\"Coco\",\"popularity\":156.998163,\"poster_path\":\"\\/eKi8dIrr8voobbaGzDpe8w0PVbC.jpg\",\"original_language\":\"en\",\"original_title\":\"Coco\",\"genre_ids\":[12,16,35,10751],\"backdrop_path\":\"\\/askg3SMvhqEl4OL52YuvdtY40Yb.jpg\",\"adult\":false,\"overview\":\"Despite his family’s baffling generations-old ban on music, Miguel dreams of becoming an accomplished musician like his idol, Ernesto de la Cruz. Desperate to prove his talent, Miguel finds himself in the stunning and colorful Land of the Dead following a mysterious chain of events. Along the way, he meets charming trickster Hector, and together, they set off on an extraordinary journey to unlock the real story behind Miguel's family history.\",\"release_date\":\"2017-10-27\"},{\"vote_count\":1723,\"id\":359940,\"video\":false,\"vote_average\":8.2,\"title\":\"Three Billboards Outside Ebbing, Missouri\",\"popularity\":154.661221,\"poster_path\":\"\\/vgvw6w1CtcFkuXXn004S5wQsHRl.jpg\",\"original_language\":\"en\",\"original_title\":\"Three Billboards Outside Ebbing, Missouri\",\"genre_ids\":[80,18],\"backdrop_path\":\"\\/bJLJAtGjBj836UjJZNOwgrfe5Ps.jpg\",\"adult\":false,\"overview\":\"After seven months have passed without a culprit in her daughter's murder case, Mildred Hayes makes a bold move, painting three signs leading into her town with a controversial message directed at Bill Willoughby, the town's revered chief of police. When his second-in-command Officer Jason Dixon, an immature mother's boy with a penchant for violence, gets involved, the battle between Mildred and Ebbing's law enforcement is only exacerbated.\",\"release_date\":\"2017-11-10\"},{\"vote_count\":1070,\"id\":399404,\"video\":false,\"vote_average\":7.1,\"title\":\"Darkest Hour\",\"popularity\":139.093572,\"poster_path\":\"\\/xa6G3aKlysQeVg9wOb0dRcIGlWu.jpg\",\"original_language\":\"en\",\"original_title\":\"Darkest Hour\",\"genre_ids\":[18,36],\"backdrop_path\":\"\\/zXwFJMwvQcJFitP9GcHZvHAHGe8.jpg\",\"adult\":false,\"overview\":\"A thrilling and inspiring true story begins on the eve of World War II as, within days of becoming Prime Minister of Great Britain, Winston Churchill must face one of his most turbulent and defining trials: exploring a negotiated peace treaty with Nazi Germany, or standing firm to fight for the ideals, liberty and freedom of a nation. As the unstoppable Nazi forces roll across Western Europe and the threat of invasion is imminent, and with an unprepared public, a skeptical King, and his own party plotting against him, Churchill must withstand his darkest hour, rally a nation, and attempt to change the course of world history.\",\"release_date\":\"2017-11-22\"},{\"vote_count\":1597,\"id\":406990,\"video\":false,\"vote_average\":7.2,\"title\":\"What Happened to Monday\",\"popularity\":107.150349,\"poster_path\":\"\\/o6EsOqITcSzcdwD1zxBM9imdxjr.jpg\",\"original_language\":\"en\",\"original_title\":\"What Happened to Monday\",\"genre_ids\":[878,53],\"backdrop_path\":\"\\/wwZ2uXOwPkMrZSeFn9s7WFXEMg6.jpg\",\"adult\":false,\"overview\":\"In a world where families are limited to one child due to overpopulation, a set of identical septuplets must avoid being put to a long sleep by the government and dangerous infighting while investigating the disappearance of one of their own.\",\"release_date\":\"2017-08-18\"},{\"vote_count\":1376,\"id\":398818,\"video\":false,\"vote_average\":8.3,\"title\":\"Call Me by Your Name\",\"popularity\":87.879371,\"poster_path\":\"\\/nPTjj6ZfBXXBwOhd7iUy6tyuKWt.jpg\",\"original_language\":\"en\",\"original_title\":\"Call Me by Your Name\",\"genre_ids\":[10749,18],\"backdrop_path\":\"\\/vABQeCuuagIjir0YBAfL0V2XePn.jpg\",\"adult\":false,\"overview\":\"Elio Perlman is spending the summer with his family at their vacation home in Lombardy, Italy. When his father hires a handsome doctoral student, the curious 17-year-old finds himself developing a growing attraction to the young man.\",\"release_date\":\"2017-10-27\"},{\"vote_count\":837,\"id\":336843,\"video\":false,\"vote_average\":7.4,\"title\":\"Maze Runner: The Death Cure\",\"popularity\":75.442347,\"poster_path\":\"\\/7GgZ6DGezkh3szFdvskH5XD4V0t.jpg\",\"original_language\":\"en\",\"original_title\":\"Maze Runner: The Death Cure\",\"genre_ids\":[28,9648,878,53],\"backdrop_path\":\"\\/bvbyidkMaBls1LTaIWYY6UmYTaL.jpg\",\"adult\":false,\"overview\":\"Thomas leads his group of escaped Gladers on their final and most dangerous mission yet. To save their friends, they must break into the legendary Last City, a WCKD-controlled labyrinth that may turn out to be the deadliest maze of all. Anyone who makes it out alive will get answers to the questions the Gladers have been asking since they first arrived in the maze.\",\"release_date\":\"2018-01-17\"},{\"vote_count\":6828,\"id\":8587,\"video\":false,\"vote_average\":8.1,\"title\":\"The Lion King\",\"popularity\":72.894102,\"poster_path\":\"\\/bKPtXn9n4M4s8vvZrbw40mYsefB.jpg\",\"original_language\":\"en\",\"original_title\":\"The Lion King\",\"genre_ids\":[10751,16,18],\"backdrop_path\":\"\\/klI0K4oQMsLhHdjA9Uw8WLugk9v.jpg\",\"adult\":false,\"overview\":\"A young lion cub named Simba can't wait to be king. But his uncle craves the title for himself and will stop at nothing to get it.\",\"release_date\":\"1994-06-23\"},{\"vote_count\":136,\"id\":401981,\"video\":false,\"vote_average\":6.7,\"title\":\"Red Sparrow\",\"popularity\":71.668631,\"poster_path\":\"\\/uZwnaMQTdwZz1kwtrrU3IOqxnDu.jpg\",\"original_language\":\"en\",\"original_title\":\"Red Sparrow\",\"genre_ids\":[9648,53],\"backdrop_path\":\"\\/sGzuQYSTwJvLBc2PnuSVLHhuFeh.jpg\",\"adult\":false,\"overview\":\"Prima ballerina Dominika Egorova faces a bleak and uncertain future after she suffers an injury that ends her career. She soon turns to Sparrow School, a secret intelligence service that trains exceptional young people to use their minds and bodies as weapons. Egorova emerges as the most dangerous Sparrow after completing the sadistic training process. As she comes to terms with her new abilities, Dominika meets a CIA agent who tries to convince her that he is the only person she can trust.\",\"release_date\":\"2018-03-01\"},{\"vote_count\":576,\"id\":389015,\"video\":false,\"vote_average\":7.5,\"title\":\"I, Tonya\",\"popularity\":71.093142,\"poster_path\":\"\\/6gNXwSHxaksR1PjVZRqNapmkgj3.jpg\",\"original_language\":\"en\",\"original_title\":\"I, Tonya\",\"genre_ids\":[18],\"backdrop_path\":\"\\/jDai0rdlBHhcY8vpJ4XmTIDQU5i.jpg\",\"adult\":false,\"overview\":\"Competitive ice skater Tonya Harding rises amongst the ranks at the U.S. Figure Skating Championships, but her future in the activity is thrown into doubt when her ex-husband intervenes.\",\"release_date\":\"2017-12-08\"},{\"vote_count\":1260,\"id\":406997,\"video\":false,\"vote_average\":8.2,\"title\":\"Wonder\",\"popularity\":68.490508,\"poster_path\":\"\\/ouYgAatYH4JzIThj6FI3UYf31RI.jpg\",\"original_language\":\"en\",\"original_title\":\"Wonder\",\"genre_ids\":[18],\"backdrop_path\":\"\\/4rsrxYDfIzvtAsIE9wevxPRXAk4.jpg\",\"adult\":false,\"overview\":\"The story of August Pullman – a boy with facial differences – who enters fifth grade, attending a mainstream elementary school for the first time.\",\"release_date\":\"2017-11-13\"},{\"vote_count\":207,\"id\":395991,\"video\":false,\"vote_average\":6.6,\"title\":\"Only the Brave\",\"popularity\":64.619702,\"poster_path\":\"\\/lC7WdUNLOJI3sllaDGNdFy2GT8g.jpg\",\"original_language\":\"en\",\"original_title\":\"Only the Brave\",\"genre_ids\":[18],\"backdrop_path\":\"\\/8wI0M7HDHhDsoxMCkcAVUx7O6DX.jpg\",\"adult\":false,\"overview\":\"Members of the Granite Mountain Hotshots battle deadly wildfires to save an Arizona town.\",\"release_date\":\"2017-09-22\"},{\"vote_count\":337,\"id\":440597,\"video\":false,\"vote_average\":5.4,\"title\":\"Wish Upon\",\"popularity\":64.054223,\"poster_path\":\"\\/u0vnocj57vJt5DHoBEqUOD1G4SU.jpg\",\"original_language\":\"en\",\"original_title\":\"Wish Upon\",\"genre_ids\":[53,27,14],\"backdrop_path\":\"\\/l0dvARJQ6xZeVPhxSS5EYibYGBR.jpg\",\"adult\":false,\"overview\":\"A teenage girl discovers a box with magical powers, but those powers comes with a deadly price.\",\"release_date\":\"2017-07-07\"},{\"vote_count\":52,\"id\":390061,\"video\":false,\"vote_average\":5.6,\"title\":\"Mark Felt: The Man Who Brought Down the White House\",\"popularity\":54.190325,\"poster_path\":\"\\/6Oq2lQaePXJJwcguc8aqB7EpduZ.jpg\",\"original_language\":\"en\",\"original_title\":\"Mark Felt: The Man Who Brought Down the White House\",\"genre_ids\":[18,36,53],\"backdrop_path\":\"\\/lsDqGTexYBYvZ2eeagh0ORo0k1b.jpg\",\"adult\":false,\"overview\":\"The story of Mark Felt, who under the name \\\"Deep Throat\\\" helped journalists Bob Woodward and Carl Bernstein uncover the Watergate scandal in 1974.\",\"release_date\":\"2017-09-29\"},{\"vote_count\":37,\"id\":452773,\"video\":false,\"vote_average\":6.2,\"title\":\"Tad the Lost Explorer and the Secret of King Midas\",\"popularity\":49.152201,\"poster_path\":\"\\/7WtFoWtGp45PLNmajM8iGtjAA7B.jpg\",\"original_language\":\"es\",\"original_title\":\"Tadeo Jones 2: El secreto del rey Midas\",\"genre_ids\":[16,12,35],\"backdrop_path\":\"\\/lP1iXFp9cFs42LWUDyktG8Sl5oS.jpg\",\"adult\":false,\"overview\":\"Tad Jones, the most awkward explorer, must rescue his beloved Sara from a millionaire who is looking for King Midas' necklace.\",\"release_date\":\"2017-08-25\"},{\"vote_count\":1,\"id\":499772,\"video\":false,\"vote_average\":1,\"title\":\"Meet Me In St. Gallen\",\"popularity\":49.042097,\"poster_path\":\"\\/uGntNjUx6YAzbVy7RDgxWnWsdet.jpg\",\"original_language\":\"tl\",\"original_title\":\"Meet Me In St. Gallen\",\"genre_ids\":[18,10749],\"backdrop_path\":\"\\/4QrbczSQGZQA7BG9xMhccQI7LHm.jpg\",\"adult\":false,\"overview\":\"The story of Jesse and Celeste who meets at an unexpected time in their lives. They then realize their names are the same as the characters in the popular break-up romantic comedy, Celeste and Jesse Forever.\",\"release_date\":\"2018-02-07\"}],\"page\":1,\"total_results\":826,\"dates\":{\"maximum\":\"2018-03-04\",\"minimum\":\"2018-01-15\"},\"total_pages\":42}";

    /**
     * Parse the popular movies JSON response and return an {@link Movie} object
     * from the input movieJSON String
     */
    public static ArrayList<Movie> getPopularMovies() {

        // Create a new ArrayList to add movies to
        ArrayList<Movie> popularMoviesList = new ArrayList<>();

        try {
            // Create a new JSONObject with the JSON request URL
            JSONObject jsonResponse = new JSONObject(POPULAR_MOVIES_URL);
            // Get the JSONArray "results"
            JSONArray resultsArray = jsonResponse.getJSONArray("results");

            /*
             Starting at position 0, increment through each result until the end
             of the JSONArray is reached
              */
            for (int i = 0; i < resultsArray.length(); i++) {
                /*
                 Convert the JSONArray into a JSONObject that starts at the beginning of
                 the JSONArray
                  */
                JSONObject firstResult = resultsArray.getJSONObject(i);

                // Extract the value at the key called "title"
                String title = firstResult.getString("title");
                // Extract the value at the key called "vote_average"
                String voteAverage = firstResult.getString("vote_average");
                // Extract the value at the key called "poster_path"
                String posterPath = firstResult.getString("poster_path");
                /*
                 Create a String combining the base URL and extracted poster path, which
                 creates the URL for each movie poster
                  */
                String moviePoster = POSTER_BASE_URL + posterPath;
                // Convert the movie poster String into a Uri object
                Uri posterUri = Uri.parse(moviePoster);

                /*
                 Create a new Movie object with the title, vote average, and poster from
                 the JSON response
                  */
                Movie movie = new Movie(title, voteAverage, posterUri);

                // Add the Movie object to the popular movies ArrayList
                popularMoviesList.add(movie);
            }
            // Catch a JSONException and print to logs if there was a parsing error
            // Stops the app from crashing
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the movie data", e);
        }

        // Return the list of popular movies
        return popularMoviesList;
    }
}
