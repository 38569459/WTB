package wilx.android.wtb.DataClone;

import java.io.Serializable;
import java.util.ArrayList;

import wilx.android.wtb.DataStruct.Semester;

/**
 * Created by william on 11/01/2018.
 */

public class AppDataCore implements Serializable {
    public ArrayList<Semester> semesters;

    public int maxRow;
    public int maxColumn;

    public AppDataCore()
    {
        semesters = new ArrayList<Semester>();
        maxRow = 18;
        maxColumn = 8;
    }
}
