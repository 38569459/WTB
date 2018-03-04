package wilx.android.wtb.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import wilx.android.wtb.AppResources;
import wilx.android.wtb.R;

public class SettingsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    MenuItem currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);

        drawerLayout = (DrawerLayout) findViewById(R.id.settings_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.settings_navigationview);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuClicked(item);
                return false;
            }
        });

        currentMenu = navigationView.getMenu().findItem(R.id.navigationMenu_settings);
        currentMenu.setChecked(true);

        ((ToggleButton) findViewById(R.id.settings_togglebutton)).setChecked(AppResources.AppSettings.isDeveloping);
    }

    @Override
    public void onStop() {
        AppResources.Misc.saveAppSettings(this);
        super.onStop();
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
                case R.id.navigationMenu_helps:
                {
                    Intent i = new Intent(this, HelpsActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.navigationMenu_about:
                {
                    Intent i = new Intent(this, AboutActivity.class);
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

    public View modeChange(View v) {
        AppResources.AppSettings.isDeveloping = !AppResources.AppSettings.isDeveloping;
        return v;
    }
}
