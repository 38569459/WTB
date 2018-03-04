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
import android.widget.Toast;

import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.R;
import wilx.android.wtb.SemestersListViewAdapter;

public class SemestersActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView semestersListView;
    SemestersListViewAdapter semestersListViewAdapter;

    MenuItem currentMenu;
    View semestersToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_semesters);

        drawerLayout = (DrawerLayout) findViewById(R.id.semesters_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.semesters_navigationview);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuClicked(item);
                return false;
            }
        });

        semestersListView = (ListView) findViewById(R.id.semesters_listview);
        semestersListViewAdapter = new SemestersListViewAdapter(this, 0, AppDataCore.semesters);
        semestersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!Tmp.isSelecting)
                {
                    Tmp.fromSemesters = true;
                    Tmp.invokeSemester = position;
                    Intent i = new Intent(SemestersActivity.this,RecordsActivity.class);
                    startActivity(i);
                }
            }
        });
        semestersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!Tmp.isSelecting)
                {
                    Tmp.isSelecting = true;
                    Tmp.addList.clear();
                    Tmp.selectedSemesters.clear();
                    Tmp.addList.add(position);
                    semestersToolBar.setVisibility(View.VISIBLE);
                    semestersListViewAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        semestersListView.setAdapter(semestersListViewAdapter);

        currentMenu = navigationView.getMenu().findItem(R.id.navigationMenu_semesters);
        currentMenu.setChecked(true);

        semestersToolBar = findViewById(R.id.semesters_toolbar);
        semestersToolBar.setVisibility(View.GONE);

        Tmp.isSelecting = false;

    }

    @Override
    public void onBackPressed() {
        if (Tmp.isSelecting)
        {
            Tmp.isSelecting = false;
            Tmp.selectedSemesters.clear();
            semestersToolBar.setVisibility(View.GONE);
            semestersListViewAdapter.notifyDataSetChanged();
        }
        else
            finish();
    }

    @Override
    public void onResume() {
        if (Tmp.finishYourself)
        {
            Tmp.finishYourself = false;
            finish();
        }
        super.onResume();
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

    public View addSemester(View v) {
        startActivityForResult(new Intent(this, AddSemesterActivity.class), 1);
        return v;
    }

    public View setCurrent(View v) {
        if (Tmp.selectedSemesters.size() == 0)
            Toast.makeText(this, "Chọn một!", Toast.LENGTH_LONG).show();
        else if (Tmp.selectedSemesters.size() > 1)
            Toast.makeText(this, "Chỉ chọn một!", Toast.LENGTH_LONG).show();
        else if (Tmp.selectedSemesters.get(0) != AppDataSide.currentSemester)
        {
            AppDataSide.currentSemester = Tmp.selectedSemesters.get(0);
            Tmp.selectedSemesters.clear();
            Tmp.isSelecting = false;
            semestersToolBar.setVisibility(View.GONE);
            Misc.saveAppDataSide(this);
            Tmp.dataChanged = true;

            semestersListViewAdapter.notifyDataSetChanged();
        }
        else
        {
            Tmp.isSelecting = false;
            semestersToolBar.setVisibility(View.GONE);
            semestersListViewAdapter.notifyDataSetChanged();
        }
        return v;
    }

    public View selectAll(View v) {
        Tmp.addList.clear();
        Tmp.selectedSemesters.clear();
        for (int i = 0; i< AppDataCore.semesters.size(); ++i)
            Tmp.addList.add(i);
        semestersListViewAdapter.notifyDataSetChanged();
        return v;
    }

    public View delete(View v) {
        if (Tmp.selectedSemesters.size() == 1 )
        {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.putExtra("Message", "Delete this semester?");
            i.putExtra("LeftText", "Cancel");
            i.putExtra("RightText", "Delete");
            startActivityForResult(i, 2);
        }
        else if (Tmp.selectedSemesters.size() != 0)
        {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.putExtra("Message", "Delete these semesters?");
            i.putExtra("LeftText", "Cancel");
            i.putExtra("RightText", "Delete");
            startActivityForResult(i, 2);
        }
        else
        {
            Tmp.isSelecting = false;
            semestersToolBar.setVisibility(View.GONE);
        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int returnCode, Intent i) {
        switch (requestCode)
        {
            case 1: //Return from AddSemesterActivity
            {
                if (returnCode == 12)    //New Semester Added
                {
                    Tmp.addList.clear();
                    for (int index : Tmp.selectedSemesters)
                        Tmp.addList.add(index);
                    Tmp.selectedSemesters.clear();
                    semestersListViewAdapter.notifyDataSetChanged();
                }
                break;
            }
            case 2: //Return from ConfirmActivity
            {
                if (returnCode == 12)
                {
                    //Gui tin hieu cho TimetableActivity
                    if (Tmp.selectedSemesters.indexOf(AppDataSide.currentSemester) != -1)
                        Tmp.dataChanged = true;

                    //Hieu chinh currentSemester (Part 1)
                    int a = 0;
                    for (int index : Tmp.selectedSemesters)
                        if (index < AppDataSide.currentSemester)
                            ++a;
                    AppDataSide.currentSemester -= a;

                    //Bat dau xoa
                    for (int j=0; j< Tmp.selectedSemesters.size(); ++j)
                    {
                        //Xoa
                        AppDataCore.semesters.remove((int)Tmp.selectedSemesters.get(j));
                        //Hieu chinh cac index trong Tmp.selectedSemesters
                        for (int x=j+1; x<Tmp.selectedSemesters.size(); ++x)
                            if (Tmp.selectedSemesters.get(x) > Tmp.selectedSemesters.get(j))
                                Tmp.selectedSemesters.set(x, Tmp.selectedSemesters.get(x) - 1);
                    }

                    //Hieu chinh currentSemester (Part 2)
                    if (AppDataSide.currentSemester == AppDataCore.semesters.size())
                        AppDataSide.currentSemester = AppDataCore.semesters.size() - 1;

                    Tmp.selectedSemesters.clear();
                    Tmp.isSelecting = false;
                    semestersToolBar.setVisibility(View.GONE);
                    Misc.saveAppDataCore(this);
                    Misc.saveAppDataSide(this);

                    semestersListViewAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }


}
