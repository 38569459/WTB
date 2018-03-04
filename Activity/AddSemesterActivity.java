package wilx.android.wtb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.DataStruct.Semester;
import wilx.android.wtb.R;

public class AddSemesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_semester);
    }

    public View save(View v) {
        String a = ((EditText)findViewById(R.id.add_semester_editText)).getText().toString();
        if (a.length() != 0)
        {
            AppDataCore.semesters.add(new Semester(a));
            if (AppDataCore.semesters.size() == 1)
            {
                AppDataSide.currentSemester = 0;
                AppDataSide.currentWeek = 0;
                Misc.saveAppDataSide(this);
                Tmp.dataChanged = true;
            }
            Misc.saveAppDataCore(this);
            setResult(12);
            finish();
        }
        else
            Toast.makeText(this, "Please enter semester name", Toast.LENGTH_SHORT).show();
        return v;
    }

    public View cancel(View v) {
        finish();
        return v;
    }
}
