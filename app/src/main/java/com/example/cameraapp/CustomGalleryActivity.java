package com.example.cameraapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;

public class CustomGalleryActivity extends AppCompatActivity {
    ArrayList<String> f = new ArrayList<>(); // list of file paths
    File[] lstFile;
    private String folderName = "MyPhotoDir";

    // Creating object of ViewPager
    ViewPager mViewPager;

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getFromSdCard();

        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.vpMain);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(this, f);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    public void getFromSdCard() {
        File file = new File(getExternalFilesDir(folderName), "/");

        if (file.isDirectory()) {
            lstFile = file.listFiles();

            for (int i = 0; i < lstFile.length; i++) {
                f.add(lstFile[i].getAbsolutePath());
            }
        }
    }
}
