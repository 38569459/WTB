package wilx.android.wtb.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.R;
import wilx.android.wtb.RecordsListViewAdapter;

public class RecordsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    View recordsMain, recordsNoContent;

    ListView recordsListView;
    RecordsListViewAdapter recordsListViewAdapter;

    MenuItem currentMenu;
    View recordsToolBar;

    int currentSemester;
    boolean inited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_records);

        drawerLayout = (DrawerLayout) findViewById(R.id.records_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.records_navigationview);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuClicked(item);
                return false;
            }
        });

        recordsMain = findViewById(R.id.records_main);
        recordsNoContent = findViewById(R.id.records_nocontent);

        recordsListView = (ListView) findViewById(R.id.records_listview);

        if(Tmp.fromSemesters)
            currentSemester = Tmp.invokeSemester;
        else
            currentSemester = AppDataSide.currentSemester;

        recordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!Tmp.isSelecting)
                {
                    Intent i = new Intent(RecordsActivity.this, ViewRecordActivity.class);
                    i.putExtra("currentSemester", currentSemester);
                    i.putExtra("currentRecord", position);
                    startActivityForResult(i, 3);
                }
            }
        });
        recordsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!Tmp.isSelecting)
                {
                    Tmp.isSelecting = true;
                    Tmp.addList.clear();
                    Tmp.selectedRecords.clear();
                    Tmp.addList.add(position);
                    recordsToolBar.setVisibility(View.VISIBLE);
                    recordsListViewAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        if (AppDataSide.currentSemester != -1)
        {
            recordsListViewAdapter = new RecordsListViewAdapter(this, 0,
                    AppDataCore.semesters.get(currentSemester).records);
            recordsListView.setAdapter(recordsListViewAdapter);
            setView("main");
            inited = true;
        }
        else
        {
            setView("nocontent");
            inited = false;
        }

        currentMenu = navigationView.getMenu().findItem(R.id.navigationMenu_records);
        currentMenu.setChecked(true);

        recordsToolBar = findViewById(R.id.records_toolbar);
        recordsToolBar.setVisibility(View.GONE);

        if (Tmp.fromSemesters)
        {
            Tmp.finishYourself = true;
            Tmp.fromSemesters = false;
        }
    }

    @Override
    public void onResume()
    {
        if (Tmp.dataChanged)
        {
            if (AppDataSide.currentSemester != -1)
                setView("main");
            else
                setView("nocontent");
        }
        super.onResume();
    }

    public int setView(String name) {
        if (name.equals("main"))
        {
            recordsMain.setVisibility(View.VISIBLE);
            recordsNoContent.setVisibility(View.INVISIBLE);
            return 0;
        }
        recordsMain.setVisibility(View.INVISIBLE);
        recordsNoContent.setVisibility(View.VISIBLE);
        return 0;
    }

    @Override
    public void onBackPressed() {
        if (Tmp.isSelecting)
        {
            Tmp.isSelecting = false;
            Tmp.selectedRecords.clear();
            recordsToolBar.setVisibility(View.GONE);
            recordsListViewAdapter.notifyDataSetChanged();
        }
        else
        {
            if (Tmp.finishYourself)
                Tmp.finishYourself = false;
            finish();
        }
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
                    if (!Tmp.finishYourself)
                    {
                        Intent i = new Intent(this, SemestersActivity.class);
                        startActivity(i);
                    }
                    else
                        Tmp.finishYourself = false;
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

    public View addRecord(View v) {
        Intent i = new Intent(this, AddRecordActivity.class);
        i.putExtra("currentSemester", currentSemester);
        startActivityForResult(i, 1);
        return v;
    }

    public View selectAll(View v) {
        Tmp.addList.clear();
        Tmp.selectedRecords.clear();
        for (int i = 0; i< AppDataCore.semesters.get(currentSemester).records.size(); ++i)
            Tmp.addList.add(i);
        recordsListViewAdapter.notifyDataSetChanged();
        return v;
    }

    public View delete(View v) {
        if (Tmp.selectedRecords.size() == 1 )
        {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.putExtra("Message", "Delete this record?");
            i.putExtra("LeftText", "Cancel");
            i.putExtra("RightText", "Delete");
            startActivityForResult(i, 2);
        }
        else if (Tmp.selectedRecords.size() != 0)
        {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.putExtra("Message", "Delete these records?");
            i.putExtra("LeftText", "Cancel");
            i.putExtra("RightText", "Delete");
            startActivityForResult(i, 2);
        }
        else
        {
            Tmp.isSelecting = false;
            recordsToolBar.setVisibility(View.GONE);
        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int returnCode, Intent i) {
        switch (requestCode)
        {
            case 1: //Return from AddRecordActivity
            {
                if (returnCode == 12)    //New Record Added
                {
                    Tmp.addList.clear();
                    for (int index : Tmp.selectedRecords)
                        Tmp.addList.add(index);
                    Tmp.selectedRecords.clear();
                    recordsListViewAdapter.notifyDataSetChanged();
                }
                break;
            }
            case 2: //Return from ConfirmActivity
            {
                if (returnCode == 12)
                {
                    //Gui tin hieu cho TimetableActivity
                    if (AppDataSide.currentSemester == currentSemester)
                        Tmp.dataChanged = true;

                    //Bat dau xoa
                    for (int j=0; j< Tmp.selectedRecords.size(); ++j)
                    {
                        //Xoa
                        AppDataCore.semesters.get(currentSemester).records.remove((int)Tmp.selectedRecords.get(j));
                        //Hieu chinh cac index trong Tmp.selectedRecords
                        for (int x=j+1; x<Tmp.selectedRecords.size(); ++x)
                            if (Tmp.selectedRecords.get(x) > Tmp.selectedRecords.get(j))
                                Tmp.selectedRecords.set(x, Tmp.selectedRecords.get(x) - 1);
                    }

                    Tmp.selectedRecords.clear();
                    Tmp.isSelecting = false;
                    recordsToolBar.setVisibility(View.GONE);
                    Misc.saveAppDataCore(this);
                    recordsListViewAdapter.notifyDataSetChanged();
                }
                break;
            }
            case 3: //Return from ViewRecordActivity
            {
                if (returnCode == 12)
                    recordsListViewAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

}
