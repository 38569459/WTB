package wilx.android.wtb.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import wilx.android.wtb.AppResources;
import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.R;

public class DevelopActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    MenuItem currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!AppSettings.isDeveloping)
        {
            Toast.makeText(this, "Please enable develop mode\nGo back to Timetable", Toast.LENGTH_SHORT).show();
            finish();
        }

        setContentView(R.layout.layout_develop);

        drawerLayout = (DrawerLayout) findViewById(R.id.develop_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.develop_navigationview);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuClicked(item);
                return false;
            }
        });

        currentMenu = navigationView.getMenu().findItem(R.id.navigationMenu_develop);
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
                case R.id.navigationMenu_about:
                {
                    Intent i = new Intent(this, AboutActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
            }
        }
        drawerLayout.closeDrawers();
    }

    public View showLog(View v) {
        Intent i = new Intent(this, ShowLogActivity.class);
        i.putExtra("Extra", AppLog.content.toString());
        startActivity(i);
        return v;
    }

    public View saveAll(View v) {
        Misc.saveAppDataCore(this);
        Misc.saveAppDataSide(this);
        Misc.saveAppSettings(this);
        Misc.saveAppLog(this);
        return v;
    }

    public View saveData(View v) {
        Misc.saveAppDataCore(this);
        Misc.saveAppDataSide(this);
        return v;
    }

    public View saveSettings(View v) {
        Misc.saveAppSettings(this);
        return v;
    }

    public View saveLog(View v) {
        Misc.saveAppLog(this);
        return v;
    }

    public View clearAll(View v) {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.putExtra("Message", "Clear application data, settings, log?");
        i.putExtra("LeftText", "Cancel");
        i.putExtra("RightText", "Clear");
        startActivityForResult(i, 1);
        return v;
    }

    public View clearData(View v) {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.putExtra("Message", "Clear application data?");
        i.putExtra("LeftText", "Cancel");
        i.putExtra("RightText", "Clear");
        startActivityForResult(i, 2);
        return v;
    }

    public View clearSettings(View v) {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.putExtra("Message", "Clear application settings?");
        i.putExtra("LeftText", "Cancel");
        i.putExtra("RightText", "Clear");
        startActivityForResult(i, 3);
        return v;
    }

    public View clearLog(View v) {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.putExtra("Message", "Clear application log?");
        i.putExtra("LeftText", "Cancel");
        i.putExtra("RightText", "Clear");
        startActivityForResult(i, 4);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int returnCode, Intent i) {
        if (returnCode == 12)
        {
            switch (requestCode)
            {
                case 1: //Xac nhan xoa data, settings, log
                {
                    deleteFile("AppDataCore.wilx");
                    deleteFile("AppDataSide.wilx");
                    deleteFile("AppSettings.wilx");
                    deleteFile("AppLog.wilx");
                    deleteFile("dataCoreVersion.wilx");
                    deleteFile("dataSideVersion.wilx");
                    deleteFile("settingsVersion.wilx");
                    deleteFile("logVersion.wilx");

                    AppResources.Misc.initAppLog();
                    AppResources.Misc.syncAppLog(this);
                    AppResources.Misc.loadAppDataCore(this);
                    AppResources.Misc.loadAppDataSide(this);
                    AppResources.Misc.loadAppSettings(this);

                    if (AppResources.AppDataSide.currentSemester != -1)
                        AppDataCore.semesters.get(AppResources.AppDataSide.currentSemester).updateCells();

                    break;
                }
                case 2: //Xac nhan xoa data
                {
                    deleteFile("AppDataCore.wilx");
                    deleteFile("AppDataSide.wilx");

                    deleteFile("dataCoreVersion.wilx");
                    deleteFile("dataSideVersion.wilx");

                    AppResources.Misc.loadAppDataCore(this);
                    AppResources.Misc.loadAppDataSide(this);

                    if (AppResources.AppDataSide.currentSemester != -1)
                        AppDataCore.semesters.get(AppResources.AppDataSide.currentSemester).updateCells();

                    break;
                }
                case 3: //Xac nhan xoa settings
                {
                    deleteFile("AppSettings.wilx");

                    deleteFile("settingsVersion.wilx");

                    AppResources.Misc.loadAppSettings(this);

                    break;
                }
                case 4: //Xac nhan xoa log
                {
                    deleteFile("AppLog.wilx");

                    deleteFile("logVersion.wilx");

                    AppResources.Misc.initAppLog();
                    AppResources.Misc.syncAppLog(this);

                    break;
                }
            }
        }

    }
}
