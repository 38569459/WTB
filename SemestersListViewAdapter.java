package wilx.android.wtb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import wilx.android.wtb.DataStruct.Semester;
import wilx.android.wtb.AppResources.*;

/**
 * Created by william on 11/01/2018.
 */

public class SemestersListViewAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Semester> array;

    public SemestersListViewAdapter(Context context, int resource, ArrayList<Semester> array) {
        super(context, resource, array);
        this.context = context;
        this.array = array;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_semesters_listview_item, null);
        TextView t = (TextView)v.findViewById(R.id.semesters_listview_item_name);

        t.setText(array.get(position).name);

        CheckBox c = (CheckBox)v.findViewById(R.id.semesters_listview_item_checkBox);

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Tmp.selectedSemesters.add(position);
                else
                    Tmp.selectedSemesters.remove(Tmp.selectedSemesters.indexOf(position));
            }
        });

        if (position == AppDataSide.currentSemester)
            t.setTextColor(0xFF00C0FF);

        if (Tmp.addList.indexOf(position) != -1)
        {
            c.setChecked(true);
            Tmp.addList.remove(Tmp.addList.indexOf(position));
        }

        if (Tmp.isSelecting)
            c.setVisibility(View.VISIBLE);
        else
            c.setVisibility(View.GONE);
        return v;
    }
}
