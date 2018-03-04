package wilx.android.wtb.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import wilx.android.wtb.R;

public class AboutActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    MenuItem currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);

        drawerLayout = (DrawerLayout) findViewById(R.id.about_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.about_navigationview);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuClicked(item);
                return false;
            }
        });

        currentMenu = navigationView.getMenu().findItem(R.id.navigationMenu_about);
        currentMenu.setChecked(true);
    }

    public void menuClicked(MenuItem item) {
        if(item!=currentMenu)
        {
            switch (item.getItemId())
            {
                case R.id.navigationMenu_timetable:
                {
                    finish();
                    break;
                }
                case R.id.navigationMenu_semesters:
                {
                    Intent i = new Intent(this, SemestersActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.navigationMenu_records:
                {
                    Intent i = new Intent(this, RecordsActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.navigationMenu_settings:
                {
                    Intent i = new Intent(this, SettingsActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.navigationMenu_helps:
                {
                    Intent i = new Intent(this, HelpsActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.navigationMenu_develop:
                {
                    Intent i = new Intent(this, DevelopActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
            }
        }
        drawerLayout.closeDrawers();
    }
}
