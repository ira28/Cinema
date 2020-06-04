package com.example.asistensi1dan2tahap1.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.asistensi1dan2tahap1.R;
import com.example.asistensi1dan2tahap1.adapters.MovieAdapter;
import com.example.asistensi1dan2tahap1.adapters.MovieItemClickListener;
import com.example.asistensi1dan2tahap1.adapters.SliderPagerAdapter;
import com.example.asistensi1dan2tahap1.models.Movie;
import com.example.asistensi1dan2tahap1.models.Slide;
import com.example.asistensi1dan2tahap1.models.Star;
import com.example.asistensi1dan2tahap1.models.TVShow;
import com.example.asistensi1dan2tahap1.ui.movies.AllMovieActivity;
import com.example.asistensi1dan2tahap1.ui.movies.MovieDetailActivity;
import com.example.asistensi1dan2tahap1.ui.tv_shows.TVShowsActivity;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MovieItemClickListener {

    private List<Slide> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV, UpcomingRV, NowPlayingRV, SemuaFilmRV, TVShowRV;
    private TextView TVDetailAllMovie, TVDetailTVShow;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#303960")));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>ProfileFragment</font>"));


        sliderpager = root.findViewById(R.id.slider_pager) ;
        indicator = root.findViewById(R.id.indicator);
        MoviesRV = root.findViewById(R.id.Rv_movies);
        UpcomingRV = root.findViewById(R.id.Rv_Upcoming);
        NowPlayingRV = root.findViewById(R.id.Rv_Nowplaying);
        SemuaFilmRV = root.findViewById(R.id.Rv_SemuaFilm);
        TVShowRV = root.findViewById(R.id.Rv_TVShows);
        TVDetailAllMovie = root.findViewById(R.id.detail_all_movie);
        TVDetailTVShow = root.findViewById(R.id.detail_all_tv_show);

        TVDetailAllMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllMovieActivity.class));
            }
        });

        TVDetailTVShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TVShowsActivity.class));
            }
        });



        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.cly2,"crash Landing On You", "https://www.youtube.com/watch?v=Jer8XjMrUB4"));
        lstSlides.add(new Slide(R.drawable.goblin,"Goblin", "https://www.youtube.com/watch?v=ViuDsy7yb8M"));
        lstSlides.add(new Slide(R.drawable.cly2,"crash Landing On You", "https://www.youtube.com/watch?v=Jer8XjMrUB4"));
        lstSlides.add(new Slide(R.drawable.goblin,"Goblin", "https://www.youtube.com/watch?v=ViuDsy7yb8M"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(),lstSlides);
        sliderpager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderpager,true);

        // Recyclerview Setup
        // ini data

        List<Movie> lstMovies = getDataMovieBestPopuler();

        MovieAdapter movieAdapter = new MovieAdapter(getContext(),lstMovies,this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        List<Movie> upcomingMovies = getDataMovieUpCominng();

        MovieAdapter upcomingMovieAdapater = new MovieAdapter(getContext(),upcomingMovies,this);
        UpcomingRV.setAdapter(upcomingMovieAdapater);
        UpcomingRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        List<Movie> nowPlayingMovies = getDataNowPlaying();

        MovieAdapter nowPlayingMovieAdapater = new MovieAdapter(getContext(),nowPlayingMovies,this);
        NowPlayingRV.setAdapter(nowPlayingMovieAdapater);
        NowPlayingRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        List<Movie> semuaFilm = getDataSemuaFilm();

        MovieAdapter semuaFilmAdapter = new MovieAdapter(getContext(),semuaFilm,this);
        SemuaFilmRV.setAdapter(semuaFilmAdapter);
        SemuaFilmRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        List<Movie> TVShows = getDataTVShow();

        MovieAdapter TVShowAdapter = new MovieAdapter(getContext(),TVShows,this);
        TVShowRV.setAdapter(TVShowAdapter);
        TVShowRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        return root;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable) movie.getStar());
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("producers",movie.getProducer());
        intent.putExtra("directors",movie.getDirector());
        intent.putExtra("writers",movie.getWriter());
        intent.putExtra("genres",movie.getGenre());
        intent.putExtra("duration",movie.getMin());
        intent.putExtra("release",movie.getPlay());
        intent.putExtra("rating",movie.getRating());
        intent.putExtra("studio",movie.getStudio());
        intent.putExtra("BUNDLE",args);
        intent.putExtra("streaminglink",movie.getStreamingLink());
        intent.putExtra("description",movie.getDescription());

        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getThumbnail());

        startActivity(intent);
    }

    @Override
    public void onStarClick(Star star, ImageView starImageView) {

    }

    private ArrayList<Star> getDataStar(){
        ArrayList<Star> stars = new ArrayList<>();

        stars.add(new Star(R.drawable.anna_kendrick, "Anna Kendrick", "Poppy"));
        stars.add(new Star(R.drawable.justin_timberlake, "Justin Timberlake", "Branch"));
        stars.add(new Star(R.drawable.rachel_bloom, "Rachel Bloom", "Bloom"));
        stars.add(new Star(R.drawable.kelly_clarkson, "Kelly Clarkson", "Kelly"));
        stars.add(new Star(R.drawable.james_corden, "James Corden", "James"));

        return stars;
    }

    private List<Movie> getDataNowPlaying(){
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Star> stars = getDataStar();

        result.add(new Movie("The World of The married", R.drawable.married,R.drawable.cly,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Marlon Brando, Al Pacino, James Caan", "24 March 1972 (USA)","9.2/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        result.add(new Movie("At Eighteen", R.drawable.at,R.drawable.at,R.string.synopsisTrollsWorldTour, "142 Menit", "Drama", "Niki Marvin", "Frank Darabont"," Stephen King, Frank Darabont", "Tim Robbins, Morgan Freeman, Bob Gunton", "14 October 1994 (USA)","9.3/10" ,"Castle Rock Entertaiment", "https://www.youtube.com/watch?v=6hB3S9bIaco" ,stars));

        result.add(new Movie("Beautiful World", R.drawable.beautiful,R.drawable.beautiful,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Al Pacino, Robert De Niro, Robert Duvall", "18 December 1974 (USA)","9.0/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        result.add(new Movie("If We Were A Season", R.drawable.if_we_were_a_season,R.drawable.if_we_were_a_season,R.string.synopsisTrollsWorldTour, "153", "Action, Crime, Drama", "Emma Thomas", "Christopher Nolan","Jonathan Nolan, Christpoher Nolan", "Christian Bale, Heath Ledger, Aaron Eckhart", "18 July 2008 (USA)","9.0/10" ,"Warner Bross", "https://www.youtube.com/watch?v=EXeTwQWrcwY" ,stars));

        result.add(new Movie("Healer", R.drawable.healer,R.drawable.healer,R.string.synopsisTrollsWorldTour, "96 Menit", "Crime, Drama", "Henry Fonda", "Sidney Lumet","Reginald Rose", "Henry Fonda, Lee J. Cobb, Martin Balsam", "10 April 1957 (USA)","8.9/10" ,"Orion-Nova Productions\n Bross", "https://www.youtube.com/watch?v=_13J_9B5jEk" ,stars));

        return result;
    }

    private List<Movie> getDataMovieBestPopuler(){
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Star> stars = getDataStar();

        result.add(new Movie("The K2", R.drawable.k2,R.drawable.k2,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Marlon Brando, Al Pacino, James Caan", "24 March 1972 (USA)","9.2/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        result.add(new Movie("Ending Again", R.drawable.ending_again,R.drawable.ending_again,R.string.synopsisTrollsWorldTour, "142 Menit", "Drama", "Niki Marvin", "Frank Darabont"," Stephen King, Frank Darabont", "Tim Robbins, Morgan Freeman, Bob Gunton", "14 October 1994 (USA)","9.3/10" ,"Castle Rock Entertaiment", "https://www.youtube.com/watch?v=6hB3S9bIaco" ,stars));

        result.add(new Movie("ID: Gangnam Beauty", R.drawable.beauty,R.drawable.beauty,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Al Pacino, Robert De Niro, Robert Duvall", "18 December 1974 (USA)","9.0/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        result.add(new Movie("Detective", R.drawable.detective,R.drawable.detective,R.string.synopsisTrollsWorldTour, "153", "Action, Crime, Drama", "Emma Thomas", "Christopher Nolan","Jonathan Nolan, Christpoher Nolan", "Christian Bale, Heath Ledger, Aaron Eckhart", "18 July 2008 (USA)","9.0/10" ,"Warner Bross", "https://www.youtube.com/watch?v=EXeTwQWrcwY" ,stars));

        result.add(new Movie("Mood Maker", R.drawable.mood_maker,R.drawable.mood_maker,R.string.synopsisTrollsWorldTour, "96 Menit", "Crime, Drama", "Henry Fonda", "Sidney Lumet","Reginald Rose", "Henry Fonda, Lee J. Cobb, Martin Balsam", "10 April 1957 (USA)","8.9/10" ,"Orion-Nova Productions\n Bross", "https://www.youtube.com/watch?v=_13J_9B5jEk" ,stars));

        return result;
    }

    private List<Movie> getDataMovieUpCominng(){
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Star> stars = getDataStar();

        result.add(new Movie("Have A Nice Dissert", R.drawable.have_a_nice_dissert,R.drawable.have_a_nice_dissert,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Marlon Brando, Al Pacino, James Caan", "24 March 1972 (USA)","9.2/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        result.add(new Movie("AKnowing Bros", R.drawable.knowing_brows,R.drawable.knowing_brows,R.string.synopsisTrollsWorldTour, "142 Menit", "Drama", "Niki Marvin", "Frank Darabont"," Stephen King, Frank Darabont", "Tim Robbins, Morgan Freeman, Bob Gunton", "14 October 1994 (USA)","9.3/10" ,"Castle Rock Entertaiment", "https://www.youtube.com/watch?v=6hB3S9bIaco" ,stars));

        result.add(new Movie("Mad Dog ", R.drawable.mad_dog,R.drawable.mad_dog,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Al Pacino, Robert De Niro, Robert Duvall", "18 December 1974 (USA)","9.0/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));


        return result;
    }

    public List<Movie> getDataSemuaFilm(){
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Star> stars = getDataStar();

        result.add(new Movie("Queen Of Mystake", R.drawable.queen_of_mystre,R.drawable.queen_of_mystre,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Marlon Brando, Al Pacino, James Caan", "24 March 1972 (USA)","9.2/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        result.add(new Movie("The Leady From 406", R.drawable.the_leady_from_406,R.drawable.the_leady_from_406,R.string.synopsisTrollsWorldTour, "142 Menit", "Drama", "Niki Marvin", "Frank Darabont"," Stephen King, Frank Darabont", "Tim Robbins, Morgan Freeman, Bob Gunton", "14 October 1994 (USA)","9.3/10" ,"Castle Rock Entertaiment", "https://www.youtube.com/watch?v=6hB3S9bIaco" ,stars));

        result.add(new Movie("The Wire", R.drawable.the_wire,R.drawable.the_wire,R.string.synopsisTrollsWorldTour, "172 Menit", "Crime, Drama", "Albert S. Ruddy", "Francis Ford Coppola","Mario Puzo, Francis Ford Coppola", "Al Pacino, Robert De Niro, Robert Duvall", "18 December 1974 (USA)","9.0/10" ,"Paramount Picture", "https://www.youtube.com/watch?v=sY1S34973zA" ,stars));

        return result;
    }

    public  List<Movie> getDataTVShow(){
        ArrayList<Movie> result = new ArrayList<>();

        result.add(new Movie("Runing Man ", R.drawable.runing_man));
        result.add(new Movie("1 Night 2 Day", R.drawable.one_night_two_day));
        result.add(new Movie("Knowing Bros", R.drawable.knowing_brows));

        return result;
    }
}

