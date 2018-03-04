package wilx.android.wtb.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.R;

public class TimeTableActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    View timetableMain, timetableNoContent;

    GridLayout gridLayout;
    TextView timetable_currentSemester, timetable_currentWeek;

    MenuItem currentMenu;

    boolean quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timetable);


        drawerLayout = (DrawerLayout) findViewById(R.id.timetable_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.timetable_navigationview);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuClicked(item);
                return false;
            }
        });

        timetableMain = findViewById(R.id.timetable_main);
        timetableNoContent = findViewById(R.id.timetable_nocontent);

        gridLayout = (GridLayout) findViewById(R.id.timetable_gridlayout);
        timetable_currentSemester = (TextView) findViewById(R.id.timetable_currentsemester);
        timetable_currentWeek = (TextView) findViewById(R.id.timetable_currentweek);

        currentMenu = navigationView.getMenu().findItem(R.id.navigationMenu_timetable);

        quit = false;

        init();
        if (AppDataSide.currentSemester != -1)
        {
            setView("main");
            updateView();
        }
        else
            setView("nocontent");
    }

    @Override
    public void onResume() {
        if (Tmp.dataChanged)
        {
            if (AppDataSide.currentSemester != -1)
            {
                setView("main");
                AppDataCore.semesters.get(AppDataSide.currentSemester).updateCells();
                updateView();
            }
            else
                setView("nocontent");
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.START))
            drawerLayout.closeDrawer(Gravity.START);
        else
            if(quit)
            {
                Misc.saveAppLog(this);
                super.onBackPressed();
            }
            else
            {
                Toast.makeText(this, "Press back again to quit", Toast.LENGTH_SHORT).show();
                quit = true;
            }
    }

    public void menuClicked(MenuItem item) {
        if(item!=currentMenu)
        {
            switch (item.getItemId())
            {
                case R.id.navigationMenu_semesters:
                {
                    Intent i = new Intent(this, SemestersActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.navigationMenu_records:
                {
                    Intent i = new Intent(this, RecordsActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.navigationMenu_settings:
                {
                    Intent i = new Intent(this, SettingsActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.navigationMenu_helps:
                {
                    Intent i = new Intent(this, HelpsActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.navigationMenu_about:
                {
                    Intent i = new Intent(this, AboutActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.navigationMenu_develop:
                {
                    Intent i = new Intent(this, DevelopActivity.class);
                    startActivity(i);
                    break;
                }
            }
        }
        drawerLayout.closeDrawers();
    }

    public void init() {
        //TODO: chua hoan thien
        //thieu handle cho su kien click vao textview
        //chua gan tag cho textview
        TextView t;
        for(int i = 0, c = 0, r = 0; i< AppDataCore.maxColumn* AppDataCore.maxRow; ++i, ++c)
        {
            if(c == AppDataCore.maxColumn) { c=0;    ++r; }
            t = new TextView(this);
            t.setText("C: " + Integer.toString(c) + " R: " + Integer.toString(r));
            gridLayout.setBackgroundColor(AppSettings.gridLineColor);
            GridLayout.LayoutParams l = new GridLayout.LayoutParams();
            l.columnSpec = GridLayout.spec(c);
            l.rowSpec = GridLayout.spec(r);
            l.width = (int) (getResources().getDimension(R.dimen.textView_width));
            l.height = (int) (getResources().getDimension(R.dimen.textView_height));
            l.leftMargin = 1;
            l.rightMargin = 1;
            l.topMargin = 1;
            l.bottomMargin = 1;
            if(c == 0)
                l.leftMargin = 0;
            if(r == 0)
                l.topMargin = 2;
            t.setLayoutParams(l);
            t.setGravity(Gravity.CENTER);
            t.setBackgroundColor(0xfffafafa);
            int a = r*8 + c;
            t.setTag(Integer.toString(a));
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = AppDataSide.currentWeek*8*18 + Integer.parseInt(v.getTag().toString());
                    String b = Tmp.cells[a].fillText;
                    if (b.length() != 0)
                    {
                        Intent i =new Intent(TimeTableActivity.this, ShowTextActivity.class);
                        i.putExtra("Extra", b);
                        startActivity(i);
                    }
                }
            });
            gridLayout.addView(t);
        }
        currentMenu.setChecked(true);
    }

    public int setView(String name) {
        if (name.equals("main"))
        {
            timetableMain.setVisibility(View.VISIBLE);
            timetableNoContent.setVisibility(View.INVISIBLE);
            return 0;
        }
        timetableMain.setVisibility(View.INVISIBLE);
        timetableNoContent.setVisibility(View.VISIBLE);
        return 0;
    }

    public void updateView() {
        String tmp;
        TextView t;
        timetable_currentSemester.setText("Semester : " + AppDataCore.semesters.get(AppDataSide.currentSemester).name);
        timetable_currentWeek.setText("Week : " + Integer.toString(AppDataSide.currentWeek + 1));

        for(int i = 0, c = 0, r = 0; i< AppDataCore.maxColumn* AppDataCore.maxRow; ++i, ++c)
        {
            if(c == AppDataCore.maxColumn) { c=0;    ++r; }
            t = (TextView) gridLayout.getChildAt(r* AppDataCore.maxColumn + c);
            int a = AppDataSide.currentWeek* AppDataCore.maxColumn* AppDataCore.maxRow + r* AppDataCore.maxColumn + c;
            t.setText(Tmp.cells[a].shortText);
            tmp = Tmp.cells[a].fillText;
            if (tmp.length() !=0)
            {
                if (Tmp.cells[a].cellColor == 0)    //CS1
                    t.setBackgroundColor(0xFFF0A0FF);
                if (Tmp.cells[a].cellColor == 1)    //CS2
                    t.setBackgroundColor(0xFF00E0FF);
            }
            else
                t.setBackgroundColor(0xfffafafa);
        }
        return;
    }

    public View previousWeek(View v) {
        --AppDataSide.currentWeek;
        if (AppDataSide.currentWeek == -1)
            AppDataSide.currentWeek = 52;
        Misc.saveAppDataSide(this);
        updateView();
        return v;
    }

    public View nextWeek(View v) {
        ++AppDataSide.currentWeek;
        if (AppDataSide.currentWeek == 53)
            AppDataSide.currentWeek = 0;
        Misc.saveAppDataSide(this);
        updateView();
        return v;
    }
}
