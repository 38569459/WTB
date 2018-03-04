package wilx.android.wtb.DataStruct;

import java.io.Serializable;

/**
 * Created by william on 20/11/2017.
 */

public class Cell implements Serializable
{

    public String shortText;
    public String userText;
    public String fillText;
    public int cellColor;

    public Cell() {
        shortText = new String();
        userText = new String();
        fillText = new String();
        cellColor = -1;
    }

}
