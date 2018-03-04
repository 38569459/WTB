package wilx.android.wtb.DataClone;

import java.io.Serializable;

import wilx.android.wtb.DataStruct.Semester;

/**
 * Created by william on 11/01/2018.
 */

public class AppSettings implements Serializable
{

    public int gridLineColor;
    public boolean isDeveloping;

    public AppSettings() {
        gridLineColor = 0xFF000000;
        isDeveloping = false;
    }
}
