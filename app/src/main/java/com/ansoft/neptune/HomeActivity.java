package com.ansoft.neptune;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.ansoft.neptune.Adapter.GridSongListAdapter;
import com.ansoft.neptune.Constants.PC;
import com.ansoft.neptune.Data.Song;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    ArrayList<Song> songList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        songList=new ArrayList<>();
        listView =(ListView)findViewById(R.id.listView);
        retrieveSongs();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void retrieveSongs(){
        ParseQuery<ParseObject> query=new ParseQuery<ParseObject>(PC.KEY_SONG_OBJECT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                Log.e("SIZE", list.size()+"");
                if (e==null){
                    for (ParseObject song:list){
                        Song song1=new Song();
                        song1.setName(song.getString(PC.KEY_SONG_NAME));
                        song1.setSongLink(song.getString(PC.KEY_SONG_LINK));
                        song1.setThumbnailLink(song.getString(PC.KEY_SONG_THUMBNAIL));
                        song1.setArtistName(song.getString(PC.KEY_SONG_ARTIST_NAME));
                        songList.add(song1);


                        Log.e("Name", song1.getName());
                        Log.e("Artist", song1.getArtistName());
                        Log.e("Link", song1.getSongLink());
                    }
                    GridSongListAdapter adapter=new GridSongListAdapter(songList, HomeActivity.this);
                    listView.setAdapter(adapter);

                }
            }
        });
    }
}
