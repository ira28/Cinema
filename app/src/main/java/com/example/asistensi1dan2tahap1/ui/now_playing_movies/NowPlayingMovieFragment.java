package com.example.asistensi1dan2tahap1.ui.now_playing_movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asistensi1dan2tahap1.R;
import com.example.asistensi1dan2tahap1.adapters.NowPlayingMovieAdapter;
import com.example.asistensi1dan2tahap1.models.Movie;
import com.example.asistensi1dan2tahap1.models.Star;
import com.example.asistensi1dan2tahap1.ui.movies.MovieDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class NowPlayingMovieFragment extends Fragment {

    private NowPlayingMovieViewModel mViewModel;
    private ListView LSNowPlaying;
    public static NowPlayingMovieFragment newInstance() {
        return new NowPlayingMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#303960")));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>ProfileFragment</font>"));
        View root = inflater.inflate(R.layout.fragment_now_playing_movie, container, false);

        LSNowPlaying = (ListView) root.findViewById(R.id.LS_NowPlaying);

        ArrayList nowPlayingList = getDataNowPlaying();

        NowPlayingMovieAdapter nowPlayingMovieAdapter = new NowPlayingMovieAdapter(getContext(), nowPlayingList);
        LSNowPlaying.setAdapter(nowPlayingMovieAdapter);
        LSNowPlaying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Movie movie = (Movie) LSNowPlaying.getItemAtPosition(position);

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
        });


        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NowPlayingMovieViewModel.class);
        // TODO: Use the ViewModel
    }

    private ArrayList getDataNowPlaying(){
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Star> stars = new ArrayList<>();

        stars.add(new Star(R.drawable.anna_kendrick, "Anna Kendrick", "Poppy"));
        stars.add(new Star(R.drawable.justin_timberlake, "Justin Timberlake", "Branch"));
        stars.add(new Star(R.drawable.rachel_bloom, "Rachel Bloom", "Bloom"));
        stars.add(new Star(R.drawable.kelly_clarkson, "Kelly Clarkson", "Kelly"));
        stars.add(new Star(R.drawable.james_corden, "James Corden", "James"));

        result.add(new Movie("Falling In Love", R.drawable.falling_in_love,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "120 Menit", "Animation, Adventure, Comedy", "Gina Shay", "Walter Dohrn","Jonathan Aibel, Glenn Berger", "Anna Kendrick, Justin Timberlake, Rachel Bloom", "April","-" ,"Universal Pictures", "https://www.youtube.com/watch?v=yP86-TR6IME" ,stars));

        result.add(new Movie("Best Mistake", R.drawable.best_mystek,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "110 Menit", "Comedy", "Suzanne Todd", "Jon Lucas, Scott Moore","Jon Lucas, Scott Moore", "Adam Devine, Rose Byrne, Alexandra Shipp", "Oktober","-" ,"Entertaiment One", "https://www.youtube.com/watch?v=txSOaY-je-o" ,stars));

        result.add(new Movie("Ending Again", R.drawable.ending_again,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "126 Menit", "Comedy, Romance", "Gope T Samtani, Sunil Samtani", "Chandra Liow","Jovial da Lopez", "Andovi da Lopez, Jovial da Lopez, Tommy Limmm", "Maret","-" ,"Rapi Films", "https://www.youtube.com/watch?v=xSdAC9WSJ5s" ,stars));

        result.add(new Movie("Wish Woosh 2", R.drawable.wish,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "110 Menit", "Romance", "Agus Basuki, Ody Mulya Hidayat", "Eddy Prasetya","Nana Mulyana, Media Kita", "Salshabilla Andriani, Endi Arfian, H. Mahyeldi SP", "April","-" ,"MAX Pictures", "https://www.youtube.com/watch?v=KGXTYsZE5Vo" ,stars));

        result.add(new Movie("Have a Nice DessertHave", R.drawable.have_a_nice_dissert,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "155 Menit", "Drama, Music, Romance", "Kevin Downes, Andrew Erwin", "Andrew Erwin, Jon Erwin","Jon Erwin, Jon Gunn", "K.J. Apa, Britt Robertson, Abigail Cowen", "Maret","6.5/10" ,"Lionsgate", "https://www.youtube.com/watch?v=YnxHyBbYwQQ" ,stars));

        result.add(new Movie("Queen Of Mystake", R.drawable.queen_of_mystre,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "197 menit", "Horror, Thriller", "Allen Liu, Tom Waller", "Tom Waller","Tom Waller", "Maythavee Burapasing, Ron Smoorenburg, Kelly B. Jones", "Maret","5.1/10" ,"De Warrenner Picture", "https://www.youtube.com/watch?v=GloDhxD_bbc" ,stars));

        result.add(new Movie("Falling In Love", R.drawable.falling_in_love,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "120 Menit", "Animation, Adventure, Comedy", "Gina Shay", "Walter Dohrn","Jonathan Aibel, Glenn Berger", "Anna Kendrick, Justin Timberlake, Rachel Bloom", "April","-" ,"Universal Pictures", "https://www.youtube.com/watch?v=yP86-TR6IME" ,stars));

        result.add(new Movie("Best Mistake", R.drawable.best_mystek,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "110 Menit", "Comedy", "Suzanne Todd", "Jon Lucas, Scott Moore","Jon Lucas, Scott Moore", "Adam Devine, Rose Byrne, Alexandra Shipp", "Oktober","-" ,"Entertaiment One", "https://www.youtube.com/watch?v=txSOaY-je-o" ,stars));

        result.add(new Movie("Ending Again", R.drawable.ending_again,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "126 Menit", "Comedy, Romance", "Gope T Samtani, Sunil Samtani", "Chandra Liow","Jovial da Lopez", "Andovi da Lopez, Jovial da Lopez, Tommy Limmm", "Maret","-" ,"Rapi Films", "https://www.youtube.com/watch?v=xSdAC9WSJ5s" ,stars));

        result.add(new Movie("Wish Woosh 2", R.drawable.wish,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "110 Menit", "Romance", "Agus Basuki, Ody Mulya Hidayat", "Eddy Prasetya","Nana Mulyana, Media Kita", "Salshabilla Andriani, Endi Arfian, H. Mahyeldi SP", "April","-" ,"MAX Pictures", "https://www.youtube.com/watch?v=KGXTYsZE5Vo" ,stars));

        result.add(new Movie("Have a Nice DessertHave", R.drawable.have_a_nice_dissert,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "155 Menit", "Drama, Music, Romance", "Kevin Downes, Andrew Erwin", "Andrew Erwin, Jon Erwin","Jon Erwin, Jon Gunn", "K.J. Apa, Britt Robertson, Abigail Cowen", "Maret","6.5/10" ,"Lionsgate", "https://www.youtube.com/watch?v=YnxHyBbYwQQ" ,stars));

        result.add(new Movie("Queen Of Mystake", R.drawable.queen_of_mystre,R.drawable.cly2,R.string.synopsisTrollsWorldTour, "197 menit", "Horror, Thriller", "Allen Liu, Tom Waller", "Tom Waller","Tom Waller", "Maythavee Burapasing, Ron Smoorenburg, Kelly B. Jones", "Maret","5.1/10" ,"De Warrenner Picture", "https://www.youtube.com/watch?v=GloDhxD_bbc" ,stars));




        return result;
    }

}
