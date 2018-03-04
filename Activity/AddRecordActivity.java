package wilx.android.wtb.Activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import wilx.android.wtb.AppResources;
import wilx.android.wtb.DataStruct.Record;
import wilx.android.wtb.R;

public class AddRecordActivity extends AppCompatActivity {

    int currentSemester;

    TextView oldDay;
    TextView oldWhere;
    int defaultColor;
    int highlightColor;

    EditText maMonHoc, tenMonHoc, tinChi, tinChiHocPhi, mucHocPhi, nhomTo, phongHoc;

    int currentDay = 0;
    boolean[] tiet = new boolean[17];   //17 tiet
    boolean[] tuan = new boolean[53];   //53 tuan
    String currentWhere;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_record);

        currentSemester = getIntent().getIntExtra("currentSemester", -1);

        maMonHoc = (EditText) findViewById(R.id.add_record_mamonhoc);
        tenMonHoc = (EditText) findViewById(R.id.add_record_tenmonhoc);
        tinChi = (EditText) findViewById(R.id.add_record_tinchi);
        tinChiHocPhi = (EditText) findViewById(R.id.add_record_tinchihocphi);
        mucHocPhi = (EditText) findViewById(R.id.add_record_muchocphi);
        nhomTo = (EditText) findViewById(R.id.add_record_nhomto);
        phongHoc = (EditText) findViewById(R.id.add_record_phonghoc);

        oldDay = (TextView) findViewById(R.id.add_record_thu2);
        oldWhere = (TextView) findViewById(R.id.add_record_cs2);

        defaultColor = oldDay.getCurrentTextColor();
        highlightColor = ContextCompat.getColor(this, R.color.colorAccent);
        oldDay.setTextColor(highlightColor);
        oldWhere.setTextColor(highlightColor);

        initData();

    }

    public View thuChange(View v) {
        oldDay.setTextColor(defaultColor);
        ((TextView) v).setTextColor(highlightColor);
        currentDay = Integer.parseInt(v.getTag().toString());
        oldDay = (TextView) v;
        return v;
    }

    public View tietChange(View v) {
        int a = Integer.parseInt(((TextView) v).getText().toString()) - 1;
        if (tiet[a])
            ((TextView) v).setTextColor(defaultColor);
        else
            ((TextView) v).setTextColor(highlightColor);
        tiet[a] = !tiet[a];
        return v;
    }

    public View tuanChange(View v) {
        int a = Integer.parseInt(((TextView) v).getText().toString()) - 1;
        if (tuan[a])
            ((TextView) v).setTextColor(defaultColor);
        else
            ((TextView) v).setTextColor(highlightColor);
        tuan[a] = !tuan[a];
        return v;
    }

    public View whereChange(View v) {
        oldWhere.setTextColor(defaultColor);
        ((TextView) v).setTextColor(highlightColor);
        currentWhere = ((TextView) v).getText().toString();
        oldWhere = (TextView) v;
        return v;
    }

    public View save(View v) {
        String a1 = maMonHoc.getText().toString();
        String a2 = tenMonHoc.getText().toString();
        String a3 = tinChi.getText().toString();
        String a4 = tinChiHocPhi.getText().toString();
        String a5 = mucHocPhi.getText().toString();
        String a6 = nhomTo.getText().toString();
        String a7 = phongHoc.getText().toString();

        if (a1.length() == 0 || a2.length() == 0 || a3.length() == 0 || a4.length() == 0 || a5.length() == 0 || a6.length() == 0 || a7.length() == 0)
            Toast.makeText(this, "Fill all field!", Toast.LENGTH_SHORT).show();
        else
        {
            int b1 = Integer.parseInt(a3);
            double b2 = Double.parseDouble(a4);
            int b3 = Integer.parseInt(a5);
            Record b = new Record(a1, a2, b1, b2, b3, a6, currentDay, tiet, a7, tuan, currentWhere);
            AppResources.AppDataCore.semesters.get(currentSemester).records.add(b);
            AppResources.AppDataCore.semesters.get(currentSemester).updateCells();
            AppResources.Misc.saveAppDataCore(this);

            //Gui tin hieu cho TimetableActivity
            if (currentSemester == AppResources.AppDataSide.currentSemester)
                AppResources.Tmp.dataChanged = true;

            setResult(12);
            finish();
        }
        return v;
    }

    public View cancel(View v) {
        finish();
        return v;
    }

    public void initData() {
        for (int i=0; i<17; ++i)
            tiet[i] = false;
        for (int i=0; i<53; ++i)
            tuan[i] = false;
        currentWhere = "CS2";
    }
}
