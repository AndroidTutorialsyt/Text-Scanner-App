package android.tutorials.textscannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Text Scanner");
        //getSupportActionBar().setSubtitle("by Android Tutorials");

        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(DashboardActivity.this, drawerLayout, R.string.open, R.string.close);
        navigationView= findViewById(R.id.navigationView);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.image:
                Intent image = new Intent(DashboardActivity.this, ImageActivity.class);
                startActivity(image);
                break;
            case R.id.camera:
                Intent camera = new Intent(DashboardActivity.this, CameraActivity.class);
                startActivity(camera);
                break;
            case R.id.feedback:
                Intent feedback = new Intent(Intent.ACTION_SENDTO);
                feedback.setData(Uri.parse("mailto:"));
                String[] mail = {"jayeshbhaikaklotar26@gmail.com"};
                String subject = "Feedback on Text Scanner // Feedback on Android Tutorials";
                String text = "Feedback on Text Scanner or Feedback on Android Tutorials YT Channel ";
                feedback.putExtra(Intent.EXTRA_SUBJECT,subject);
                feedback.putExtra(Intent.EXTRA_TEXT,text);
                feedback.putExtra(Intent.EXTRA_EMAIL,mail);
                startActivity(feedback);
                break;
            case R.id.share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("plain/text");
                String shareLink = "https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName();
                share.putExtra(Intent.EXTRA_TEXT,shareLink);
                startActivity(Intent.createChooser(share,"Share Using"));
                break;
            case R.id.rate:
                Uri  uri = Uri.parse("https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName());
                Intent rate = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(rate);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onShare(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        String shareLink = "https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName();
        intent.putExtra(Intent.EXTRA_TEXT,shareLink);
        startActivity(intent);

    }

    public void onRate(MenuItem item) {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}