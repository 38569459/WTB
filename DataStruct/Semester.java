package wilx.android.wtb.DataStruct;

import android.support.v4.content.ContextCompat;

import java.io.Serializable;
import java.util.ArrayList;

import wilx.android.wtb.AppResources.*;
import wilx.android.wtb.DataClone.AppSettings;
import wilx.android.wtb.DataStruct.*;
import wilx.android.wtb.R;

/**
 * Created by william on 20/11/2017.
 */

public class Semester implements Serializable
{

    public String name;
    public ArrayList<Record> records;

    public Semester(String name) {
        this.name = name;
        records = new ArrayList<Record>();
    }

    public void updateCells() {
        //Clear all cell
        for(int i=0; i<53; ++i) //From week 1 to week 53
            for (int j=0; j<18; ++j)   //From row 0 to row 17
                for( int x=0; x<8 ; ++x)   //Form column 0 to column 7
                {
                    int a = i*18*8 + j*8 + x;
                    Tmp.cells[a].shortText = "";
                    Tmp.cells[a].fillText = "";
                    Tmp.cells[a].cellColor = -1;
                    if (j == 0 && x!= 0)
                        if (x != 7)
                            Tmp.cells[a].shortText = Integer.toString(x + 1);
                        else
                            Tmp.cells[a].shortText = "CN";
                    if (x == 0 && j != 0)
                        Tmp.cells[a].shortText = Integer.toString(j);
                }
        //Refill all cell
        for (Record r : records)
            for (int i=0; i<53; ++i)    //Do tu tuan 1 den tuan 53
                if (r.tuanHoc[i])   //Tuan do co hoc
                    for (int j=0; j<17; ++j)    //Do tu tiet 1 den tiet 17
                        if (r.tiet[j])  //Tiet do co hoc
                        {
                            int a = i*8*18 + (j + 1)*8 + r.thu + 1;
                            Tmp.cells[a].shortText = r.phongHoc;
                            if (r.where.equals("CS1"))
                                Tmp.cells[a].cellColor = 0;
                            else
                                Tmp.cells[a].cellColor = 1;
                            Tmp.cells[a].fillText = r.fillText;
                        }
    }
}
