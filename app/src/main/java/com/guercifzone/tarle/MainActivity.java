package com.guercifzone.tarle;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.guercifzone.tarle.Classes.CustomDrawer.MenuItem;
import com.guercifzone.tarle.Classes.CustomDrawer.SNavigationDrawer;
import com.guercifzone.tarle.UIFragment.Fragment_Level1;
import com.guercifzone.tarle.UIFragment.Fragment_Level2;
import com.guercifzone.tarle.UIFragment.Fragment_Level3;
import com.guercifzone.tarle.UIFragment.Fragment_Level4;
import com.guercifzone.tarle.UIFragment.Fragment_Level5;
import com.guercifzone.tarle.UIFragment.Fragment_Level6;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    int color1 = 0;
    Class fragmentClass;
    public static Fragment fragment;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("حرف الألف",R.drawable.ic_launcher_background));
        menuItems.add(new MenuItem("حرف الباء",R.drawable.ic_launcher_background));
        menuItems.add(new MenuItem("حرف التاء",R.drawable.ic_launcher_background));
        menuItems.add(new MenuItem("حرف الثاء",R.drawable.ic_launcher_background));
        menuItems.add(new MenuItem("حرف الجيم",R.drawable.ic_launcher_background));
        menuItems.add(new MenuItem("حرف الحاء",R.drawable.ic_launcher_background));

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  Fragment_Level1.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.framelayout, fragment).commit();
        }
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);
                switch (position){
                    case 0:{
                        fragmentClass = Fragment_Level1.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = Fragment_Level2.class;
                        break;
                    }
                    case 2: {
                        fragmentClass = Fragment_Level3.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = Fragment_Level4.class;
                        break;
                    }
                    case 4:{
                        fragmentClass = Fragment_Level5.class;
                        break;
                    }
                    case 5:{
                        fragmentClass = Fragment_Level6.class;
                        break;
                    }
                }

                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                    @Override
                    public void onDrawerOpened() {

                    }
                    @Override
                    public void onDrawerOpening(){

                    }
                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.framelayout, fragment).commit();

                        }
                    }
                    @Override
                    public void onDrawerClosed() {
                    }
                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });

    }
}