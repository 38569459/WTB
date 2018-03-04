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
import wilx.android.wtb.AppResources.*;

public class ViewRecordActivity extends AppCompatActivity {

    int currentSemester;
    int currentRecord;

    TextView oldDay;
    TextView oldWhere;

    TextView edit;
    View viewRecordButtons;

    int defaultColor;
    int highlightColor;

    EditText maMonHoc, tenMonHoc, tinChi, tinChiHocPhi, mucHocPhi, nhomTo, phongHoc;

    int currentDay = 0;
    boolean[] tiet = new boolean[17];   //17 tiet
    boolean[] tuan = new boolean[53];   //53 tuan
    String currentWhere;

    boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_view_record);

        currentSemester = getIntent().getIntExtra("currentSemester", -1);
        currentRecord = getIntent().getIntExtra("currentRecord", -1);

        edit = (TextView) findViewById(R.id.view_record_edit);
        viewRecordButtons = findViewById(R.id.view_record_buttons);

        maMonHoc = (EditText) findViewById(R.id.view_record_mamonhoc);
        tenMonHoc = (EditText) findViewById(R.id.view_record_tenmonhoc);
        tinChi = (EditText) findViewById(R.id.view_record_tinchi);
        tinChiHocPhi = (EditText) findViewById(R.id.view_record_tinchihocphi);
        mucHocPhi = (EditText) findViewById(R.id.view_record_muchocphi);
        nhomTo = (EditText) findViewById(R.id.view_record_nhomto);
        phongHoc = (EditText) findViewById(R.id.view_record_phonghoc);

        defaultColor = edit.getCurrentTextColor();
        highlightColor = ContextCompat.getColor(this, R.color.colorAccent);

        resetView();
    }

    public View edit(View v) {
        if (!isEditing)
        {
            edit.setTextColor(highlightColor);
            viewRecordButtons.setVisibility(View.VISIBLE);
            maMonHoc.setEnabled(true);
            tenMonHoc.setEnabled(true);
            tinChi.setEnabled(true);
            tinChiHocPhi.setEnabled(true);
            mucHocPhi.setEnabled(true);
            nhomTo.setEnabled(true);
            phongHoc.setEnabled(true);
        }
        else
            resetView();
        isEditing = !isEditing;
        return v;
    }

    public View thuChange(View v) {
        if (isEditing)
        {
            oldDay.setTextColor(defaultColor);
            ((TextView) v).setTextColor(highlightColor);
            currentDay = Integer.parseInt(v.getTag().toString());
            oldDay = (TextView) v;
        }
        return v;
    }

    public View tietChange(View v) {
        if (isEditing)
        {
            int a = Integer.parseInt(((TextView) v).getText().toString()) - 1;
            if (tiet[a])
                ((TextView) v).setTextColor(defaultColor);
            else
                ((TextView) v).setTextColor(highlightColor);
            tiet[a] = !tiet[a];
        }
        return v;
    }

    public View tuanChange(View v) {
        if (isEditing)
        {
            int a = Integer.parseInt(((TextView) v).getText().toString()) - 1;
            if (tuan[a])
                ((TextView) v).setTextColor(defaultColor);
            else
                ((TextView) v).setTextColor(highlightColor);
            tuan[a] = !tuan[a];
        }
        return v;
    }

    public View whereChange(View v) {
        if (isEditing)
        {
            oldWhere.setTextColor(defaultColor);
            ((TextView) v).setTextColor(highlightColor);
            currentWhere = ((TextView) v).getText().toString();
            oldWhere = (TextView) v;
        }
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
            double b1 = Double.parseDouble(a3);
            double b2 = Double.parseDouble(a4);
            double b3 = Double.parseDouble(a5);
            Record b = new Record(a1, a2, b1, b2, b3, a6, currentDay, tiet, a7, tuan, currentWhere);
            AppDataCore.semesters.get(currentSemester).records.set(currentRecord, b);
            AppDataCore.semesters.get(currentSemester).updateCells();
            AppResources.Misc.saveAppDataCore(this);
            if (currentSemester == AppResources.AppDataSide.currentSemester)    //Gui tin hieu cho TimetableActivity
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

    public void resetView() {
        edit.setTextColor(defaultColor);
        viewRecordButtons.setVisibility(View.GONE);

        Record r = AppDataCore.semesters.get(currentSemester).records.get(currentRecord);

        currentDay = r.thu;
        currentWhere = r.where;

        maMonHoc.setText(r.maMonHoc);
        tenMonHoc.setText(r.tenMonHoc);
        tinChi.setText(Double.toString(r.tinChi));
        tinChiHocPhi.setText(Double.toString(r.tinChiHocPhi));
        mucHocPhi.setText(Double.toString(r.mucHocPhi));
        nhomTo.setText(r.nhomTo);
        phongHoc.setText(r.phongHoc);

        maMonHoc.setEnabled(false);
        tenMonHoc.setEnabled(false);
        tinChi.setEnabled(false);
        tinChiHocPhi.setEnabled(false);
        mucHocPhi.setEnabled(false);
        nhomTo.setEnabled(false);
        phongHoc.setEnabled(false);

        //Chinh thu
        ((TextView) findViewById(R.id.view_record_thu2)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_thu3)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_thu4)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_thu5)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_thu6)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_thu7)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_cn)).setTextColor(defaultColor);

        switch (currentDay)
        {
            case 0:
            {
                ((TextView) findViewById(R.id.view_record_thu2)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_thu2);
                break;
            }
            case 1:
            {
                ((TextView) findViewById(R.id.view_record_thu3)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_thu3);
                break;
            }
            case 2:
            {
                ((TextView) findViewById(R.id.view_record_thu4)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_thu4);
                break;
            }
            case 3:
            {
                ((TextView) findViewById(R.id.view_record_thu5)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_thu5);
                break;
            }
            case 4:
            {
                ((TextView) findViewById(R.id.view_record_thu6)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_thu6);
                break;
            }
            case 5:
            {
                ((TextView) findViewById(R.id.view_record_thu7)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_thu7);
                break;
            }
            case 6:
            {
                ((TextView) findViewById(R.id.view_record_cn)).setTextColor(highlightColor);
                oldDay = (TextView) findViewById(R.id.view_record_cn);
                break;
            }
        }

        //Chinh tiet
        ((TextView) findViewById(R.id.view_record_tiet1)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet2)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet3)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet4)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet5)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet6)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet7)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet8)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet9)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet10)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet11)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet12)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet13)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet14)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet15)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet16)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tiet17)).setTextColor(defaultColor);

        if (r.tiet[0])
            ((TextView) findViewById(R.id.view_record_tiet1)).setTextColor(highlightColor);
        if (r.tiet[1])
            ((TextView) findViewById(R.id.view_record_tiet2)).setTextColor(highlightColor);
        if (r.tiet[2])
            ((TextView) findViewById(R.id.view_record_tiet3)).setTextColor(highlightColor);
        if (r.tiet[3])
            ((TextView) findViewById(R.id.view_record_tiet4)).setTextColor(highlightColor);
        if (r.tiet[4])
            ((TextView) findViewById(R.id.view_record_tiet5)).setTextColor(highlightColor);
        if (r.tiet[5])
            ((TextView) findViewById(R.id.view_record_tiet6)).setTextColor(highlightColor);
        if (r.tiet[6])
            ((TextView) findViewById(R.id.view_record_tiet7)).setTextColor(highlightColor);
        if (r.tiet[7])
            ((TextView) findViewById(R.id.view_record_tiet8)).setTextColor(highlightColor);
        if (r.tiet[8])
            ((TextView) findViewById(R.id.view_record_tiet9)).setTextColor(highlightColor);
        if (r.tiet[9])
            ((TextView) findViewById(R.id.view_record_tiet10)).setTextColor(highlightColor);
        if (r.tiet[10])
            ((TextView) findViewById(R.id.view_record_tiet11)).setTextColor(highlightColor);
        if (r.tiet[11])
            ((TextView) findViewById(R.id.view_record_tiet12)).setTextColor(highlightColor);
        if (r.tiet[12])
            ((TextView) findViewById(R.id.view_record_tiet13)).setTextColor(highlightColor);
        if (r.tiet[13])
            ((TextView) findViewById(R.id.view_record_tiet14)).setTextColor(highlightColor);
        if (r.tiet[14])
            ((TextView) findViewById(R.id.view_record_tiet15)).setTextColor(highlightColor);
        if (r.tiet[15])
            ((TextView) findViewById(R.id.view_record_tiet16)).setTextColor(highlightColor);
        if (r.tiet[16])
            ((TextView) findViewById(R.id.view_record_tiet17)).setTextColor(highlightColor);

        for (int i=0; i<17; ++i)
            tiet[i] = r.tiet[i];

        //Chinh tuan
        ((TextView) findViewById(R.id.view_record_tuan1)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan2)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan3)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan4)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan5)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan6)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan7)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan8)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan9)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan10)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan11)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan12)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan13)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan14)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan15)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan16)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan17)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan18)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan19)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan20)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan21)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan22)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan23)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan24)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan25)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan26)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan27)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan28)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan29)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan30)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan31)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan32)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan33)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan34)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan35)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan36)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan37)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan38)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan39)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan40)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan41)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan42)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan43)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan44)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan45)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan46)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan47)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan48)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan49)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan50)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan51)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan52)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_tuan53)).setTextColor(defaultColor);

        if (r.tuanHoc[0])
            ((TextView) findViewById(R.id.view_record_tuan1)).setTextColor(highlightColor);
        if (r.tuanHoc[1])
            ((TextView) findViewById(R.id.view_record_tuan2)).setTextColor(highlightColor);
        if (r.tuanHoc[2])
            ((TextView) findViewById(R.id.view_record_tuan3)).setTextColor(highlightColor);
        if (r.tuanHoc[3])
            ((TextView) findViewById(R.id.view_record_tuan4)).setTextColor(highlightColor);
        if (r.tuanHoc[4])
            ((TextView) findViewById(R.id.view_record_tuan5)).setTextColor(highlightColor);
        if (r.tuanHoc[5])
            ((TextView) findViewById(R.id.view_record_tuan6)).setTextColor(highlightColor);
        if (r.tuanHoc[6])
            ((TextView) findViewById(R.id.view_record_tuan7)).setTextColor(highlightColor);
        if (r.tuanHoc[7])
            ((TextView) findViewById(R.id.view_record_tuan8)).setTextColor(highlightColor);
        if (r.tuanHoc[8])
            ((TextView) findViewById(R.id.view_record_tuan9)).setTextColor(highlightColor);
        if (r.tuanHoc[9])
            ((TextView) findViewById(R.id.view_record_tuan10)).setTextColor(highlightColor);
        if (r.tuanHoc[10])
            ((TextView) findViewById(R.id.view_record_tuan11)).setTextColor(highlightColor);
        if (r.tuanHoc[11])
            ((TextView) findViewById(R.id.view_record_tuan12)).setTextColor(highlightColor);
        if (r.tuanHoc[12])
            ((TextView) findViewById(R.id.view_record_tuan13)).setTextColor(highlightColor);
        if (r.tuanHoc[13])
            ((TextView) findViewById(R.id.view_record_tuan14)).setTextColor(highlightColor);
        if (r.tuanHoc[14])
            ((TextView) findViewById(R.id.view_record_tuan15)).setTextColor(highlightColor);
        if (r.tuanHoc[15])
            ((TextView) findViewById(R.id.view_record_tuan16)).setTextColor(highlightColor);
        if (r.tuanHoc[16])
            ((TextView) findViewById(R.id.view_record_tuan17)).setTextColor(highlightColor);
        if (r.tuanHoc[17])
            ((TextView) findViewById(R.id.view_record_tuan18)).setTextColor(highlightColor);
        if (r.tuanHoc[18])
            ((TextView) findViewById(R.id.view_record_tuan19)).setTextColor(highlightColor);
        if (r.tuanHoc[19])
            ((TextView) findViewById(R.id.view_record_tuan20)).setTextColor(highlightColor);
        if (r.tuanHoc[20])
            ((TextView) findViewById(R.id.view_record_tuan21)).setTextColor(highlightColor);
        if (r.tuanHoc[21])
            ((TextView) findViewById(R.id.view_record_tuan22)).setTextColor(highlightColor);
        if (r.tuanHoc[22])
            ((TextView) findViewById(R.id.view_record_tuan23)).setTextColor(highlightColor);
        if (r.tuanHoc[23])
            ((TextView) findViewById(R.id.view_record_tuan24)).setTextColor(highlightColor);
        if (r.tuanHoc[24])
            ((TextView) findViewById(R.id.view_record_tuan25)).setTextColor(highlightColor);
        if (r.tuanHoc[25])
            ((TextView) findViewById(R.id.view_record_tuan26)).setTextColor(highlightColor);
        if (r.tuanHoc[26])
            ((TextView) findViewById(R.id.view_record_tuan27)).setTextColor(highlightColor);
        if (r.tuanHoc[27])
            ((TextView) findViewById(R.id.view_record_tuan28)).setTextColor(highlightColor);
        if (r.tuanHoc[28])
            ((TextView) findViewById(R.id.view_record_tuan28)).setTextColor(highlightColor);
        if (r.tuanHoc[29])
            ((TextView) findViewById(R.id.view_record_tuan30)).setTextColor(highlightColor);
        if (r.tuanHoc[30])
            ((TextView) findViewById(R.id.view_record_tuan31)).setTextColor(highlightColor);
        if (r.tuanHoc[31])
            ((TextView) findViewById(R.id.view_record_tuan32)).setTextColor(highlightColor);
        if (r.tuanHoc[32])
            ((TextView) findViewById(R.id.view_record_tuan33)).setTextColor(highlightColor);
        if (r.tuanHoc[33])
            ((TextView) findViewById(R.id.view_record_tuan34)).setTextColor(highlightColor);
        if (r.tuanHoc[34])
            ((TextView) findViewById(R.id.view_record_tuan35)).setTextColor(highlightColor);
        if (r.tuanHoc[35])
            ((TextView) findViewById(R.id.view_record_tuan36)).setTextColor(highlightColor);
        if (r.tuanHoc[36])
            ((TextView) findViewById(R.id.view_record_tuan37)).setTextColor(highlightColor);
        if (r.tuanHoc[37])
            ((TextView) findViewById(R.id.view_record_tuan38)).setTextColor(highlightColor);
        if (r.tuanHoc[38])
            ((TextView) findViewById(R.id.view_record_tuan39)).setTextColor(highlightColor);
        if (r.tuanHoc[39])
            ((TextView) findViewById(R.id.view_record_tuan40)).setTextColor(highlightColor);
        if (r.tuanHoc[40])
            ((TextView) findViewById(R.id.view_record_tuan41)).setTextColor(highlightColor);
        if (r.tuanHoc[41])
            ((TextView) findViewById(R.id.view_record_tuan42)).setTextColor(highlightColor);
        if (r.tuanHoc[42])
            ((TextView) findViewById(R.id.view_record_tuan43)).setTextColor(highlightColor);
        if (r.tuanHoc[43])
            ((TextView) findViewById(R.id.view_record_tuan44)).setTextColor(highlightColor);
        if (r.tuanHoc[44])
            ((TextView) findViewById(R.id.view_record_tuan45)).setTextColor(highlightColor);
        if (r.tuanHoc[45])
            ((TextView) findViewById(R.id.view_record_tuan46)).setTextColor(highlightColor);
        if (r.tuanHoc[46])
            ((TextView) findViewById(R.id.view_record_tuan47)).setTextColor(highlightColor);
        if (r.tuanHoc[47])
            ((TextView) findViewById(R.id.view_record_tuan48)).setTextColor(highlightColor);
        if (r.tuanHoc[48])
            ((TextView) findViewById(R.id.view_record_tuan49)).setTextColor(highlightColor);
        if (r.tuanHoc[49])
            ((TextView) findViewById(R.id.view_record_tuan50)).setTextColor(highlightColor);
        if (r.tuanHoc[50])
            ((TextView) findViewById(R.id.view_record_tuan51)).setTextColor(highlightColor);
        if (r.tuanHoc[51])
            ((TextView) findViewById(R.id.view_record_tuan52)).setTextColor(highlightColor);
        if (r.tuanHoc[52])
            ((TextView) findViewById(R.id.view_record_tuan53)).setTextColor(highlightColor);

        for (int i=0; i<53; ++i)
            tuan[i] = r.tuanHoc[i];

        //Chinh noi hoc
        ((TextView) findViewById(R.id.view_record_cs1)).setTextColor(defaultColor);
        ((TextView) findViewById(R.id.view_record_cs2)).setTextColor(defaultColor);

        if (r.where.equals("CS1"))
        {
            ((TextView) findViewById(R.id.view_record_cs1)).setTextColor(highlightColor);
            oldWhere = (TextView) findViewById(R.id.view_record_cs1);
        }
        else
        {
            ((TextView) findViewById(R.id.view_record_cs2)).setTextColor(highlightColor);
            oldWhere = (TextView) findViewById(R.id.view_record_cs2);
        }
        currentWhere = new String(r.where);
    }
}
