package com.example.taqtile.onboard_android;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity implements DrawerAdapter.OptionClickListener {

    private List<Object> options;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerAdapter mDrawrAdapter;
    private LinearLayoutManager mLayoutManager;
    @Bind(R.id.drawer_layout_id)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.my_toolbar_id)
    public Toolbar mToolBar;
    @Bind(R.id.drawer_list_id)
    public RecyclerView optionsList;
    Fragment dummyFragment, mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        ButterKnife.bind(this);
        dummyFragment = new DummyFragment();
        mainFragment = new MainFragment();
        setInitialFragment();

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        options = factorateOptions();
        mDrawrAdapter = new DrawerAdapter(options, this);
        mLayoutManager = new LinearLayoutManager(this);

        optionsList.setAdapter(mDrawrAdapter);
        optionsList.setLayoutManager(mLayoutManager);
        optionsList.addItemDecoration(new DrawerDividerDecoration(this));

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, mToolBar,R.string.app_name,R.string.app_name);
        mDrawerToggle.syncState();


    }

    private List<Object> factorateOptions() {
        ArrayList<Object> options = new ArrayList<>();
        options.add("Hello, World from Drawer, now inside RecyclerList!");
        options.add(new Option(ContextCompat.getDrawable(this, R.drawable.ic_menu_gallery),
                "Gallery", mainFragment));
        options.add(new Option(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
                "Camera", mainFragment));
        options.add(new Option(ContextCompat.getDrawable(this, R.drawable.ic_menu_send),
                "Send", mainFragment));
        options.add(new Option(ContextCompat.getDrawable(this, R.drawable.ic_menu_slideshow),
                "Videos", dummyFragment));
        options.add(new Option(ContextCompat.getDrawable(this, R.drawable.ic_menu_manage),
                "Setting", dummyFragment));
        return options;
    }

    public void setInitialFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, mainFragment)
                .commit();
    }

    @Override
    public void onOptionClick(Option option, int position) {
        Toast.makeText(this, "You clicked at " + option.name, Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, option.fragment)
                .commit();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }
}
