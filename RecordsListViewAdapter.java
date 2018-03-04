package wilx.android.wtb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.DataStruct.*;

/**
 * Created by william on 13/01/2018.
 */

public class RecordsListViewAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Record> array;

    public RecordsListViewAdapter(Context context, int resource, ArrayList<Record> array) {
        super(context, resource, array);
        this.context = context;
        this.array = array;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_records_listview_item, null);
        TextView t = (TextView)v.findViewById(R.id.records_listview_item_name);

        t.setText(array.get(position).tenMonHoc);

        CheckBox c = (CheckBox)v.findViewById(R.id.records_listview_item_checkBox);

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Tmp.selectedRecords.add(position);
                else
                    Tmp.selectedRecords.remove(Tmp.selectedRecords.indexOf(position));
            }
        });

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
