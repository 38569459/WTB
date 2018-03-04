package wilx.android.wtb.DataClone;

import java.io.Serializable;

/**
 * Created by william on 03/02/2018.
 */

public class AppDataSide implements Serializable {
    public int currentSemester;
    public int currentWeek;

    public AppDataSide() {
        currentSemester = -1;
        currentWeek = -1;
    }

}
