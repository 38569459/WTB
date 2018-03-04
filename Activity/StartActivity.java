package wilx.android.wtb.Activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import wilx.android.wtb.*;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: them timestamp
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);

        AppResources.Misc.initTmp();
        AppResources.Misc.initAppLog();
        AppResources.Misc.syncAppLog(this);
        AppResources.Misc.loadAppDataCore(this);
        AppResources.Misc.loadAppDataSide(this);
        AppResources.Misc.loadAppSettings(this);


        if (AppResources.AppDataSide.currentSemester != -1)
            AppResources.AppDataCore.semesters.get(AppResources.AppDataSide.currentSemester).updateCells();

        Intent i = new Intent(this, TimeTableActivity.class);
        startActivity(i);
        finish();
    }
}
