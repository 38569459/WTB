package wilx.android.wtb;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import wilx.android.wtb.DataStruct.Cell;
import wilx.android.wtb.DataStruct.Semester;

/**
 * Created by william on 11/01/2018.
 */

public class AppResources {


    public static class AppDataCore {
        public static ArrayList<Semester> semesters;

        public static int maxRow;
        public static int maxColumn;

    }

    public static class AppDataSide {
        public static int currentSemester;
        public static int currentWeek;
    }

    public static class AppSettings {

        public static int gridLineColor;
        public static boolean isDeveloping;
    }

    public static class AppLog {
        public static StringBuilder content;
    }

    public static class Tmp {
        public static boolean isSelecting;
        public static ArrayList<Integer> selectedSemesters;
        public static ArrayList<Integer> selectedRecords;
        public static boolean dataChanged;
        public static boolean fromSemesters;
        public static ArrayList<Integer> addList;
        public static int invokeSemester;
        public static boolean finishYourself;
        public static Cell[] cells;
    }



    public static class Misc {
        public static void initTmp() {
            Tmp.isSelecting = false;
            Tmp.selectedSemesters = new ArrayList<Integer>();
            Tmp.selectedRecords = new ArrayList<Integer>();
            Tmp.dataChanged = false;
            Tmp.fromSemesters = false;
            Tmp.addList = new ArrayList<Integer>();
            Tmp.invokeSemester = -1;
            Tmp.finishYourself = false;
            Tmp.cells = new Cell[53*8*18];
            for(int i=0; i<53*8*18; ++i)
                Tmp.cells[i] = new Cell();
        }

        public static void initAppLog() {
            AppLog.content = new StringBuilder();
        }

        public static void syncAppLog(Context context) {
            AppLog.content.append("File: AppResources\nFunction: syncAppLog\n");
            try
            {
                AppLog.content.append("+ Begin read logVersion.wilx\n");
                FileInputStream fi = context.openFileInput("logVersion.wilx");
                ObjectInputStream oi = new ObjectInputStream(fi);
                String a = (String) oi.readObject();
                oi.close();
                fi.close();
                AppLog.content.append("+ End read logVersion.wilx\n");
                if (!a.equals(context.getString(R.string.logVersion))) {
                    AppLog.content.append("+ Old Log\n");
                    AppLog.content.append("+ Begin create new AppLog\n");
                    wilx.android.wtb.DataClone.AppLog b = new wilx.android.wtb.DataClone.AppLog();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End create new AppLog\n");
                    Toast.makeText(context, "Old log!", Toast.LENGTH_SHORT).show();
                } else {
                    AppLog.content.append("+ Begin read AppLog.wilx\n");
                    fi = context.openFileInput("AppLog.wilx");
                    oi = new ObjectInputStream(fi);
                    wilx.android.wtb.DataClone.AppLog b = (wilx.android.wtb.DataClone.AppLog) oi.readObject();
                    oi.close();
                    fi.close();
                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End read AppLog.wilx\n");
                }
            }
            catch (Exception e) {
                AppLog.content.append("+ Error occurs\n");
                AppLog.content.append("+ Begin create new AppLog\n");
                wilx.android.wtb.DataClone.AppLog b = new wilx.android.wtb.DataClone.AppLog();

                Misc.cloneFrom(b);
                AppLog.content.append("+ End create new AppLog\n");
            }
            AppLog.content.append("------------------------------------------------------------\n");
        }

        public static void loadAppDataCore(Context context) {
            AppLog.content.append("File: AppResources\nFunction: loadAppDataCore\n");
            try
            {
                AppLog.content.append("+ Begin read dataCoreVersion.wilx\n");
                FileInputStream fi = context.openFileInput("dataCoreVersion.wilx");
                ObjectInputStream oi = new ObjectInputStream(fi);
                String a = (String) oi.readObject();
                oi.close();
                fi.close();
                AppLog.content.append("+ End read dataCoreVersion.wilx\n");
                if (!a.equals(context.getString(R.string.dataCoreVersion))) {
                    AppLog.content.append("+ Old AppDataCore\n");
                    AppLog.content.append("+ Begin create new AppDataCore\n");
                    wilx.android.wtb.DataClone.AppDataCore b = new wilx.android.wtb.DataClone.AppDataCore();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End create new AppDataCore\n");
                    Toast.makeText(context, "Old AppDataCore!", Toast.LENGTH_SHORT).show();
                } else {
                    AppLog.content.append("+ Begin read AppDataCore.wilx\n");
                    fi = context.openFileInput("AppDataCore.wilx");
                    oi = new ObjectInputStream(fi);
                    wilx.android.wtb.DataClone.AppDataCore b = (wilx.android.wtb.DataClone.AppDataCore) oi.readObject();
                    oi.close();
                    fi.close();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End read AppDataCore.wilx\n");
                }
            }
            catch (Exception e) {
                AppLog.content.append("+ Error occurs\n");
                AppLog.content.append("+ Begin create new AppDataCore\n");
                wilx.android.wtb.DataClone.AppDataCore b = new wilx.android.wtb.DataClone.AppDataCore();

                Misc.cloneFrom(b);
                AppLog.content.append("+ End create new AppDataCore\n");
            }
            AppLog.content.append("------------------------------------------------------------\n");
        }

        public static void loadAppDataSide(Context context) {
            AppLog.content.append("File: AppResources\nFunction: loadAppDataSide\n");
            try
            {
                AppLog.content.append("+ Begin read dataSideVersion.wilx\n");
                FileInputStream fi = context.openFileInput("dataSideVersion.wilx");
                ObjectInputStream oi = new ObjectInputStream(fi);
                String a = (String) oi.readObject();
                oi.close();
                fi.close();
                AppLog.content.append("+ End read dataSideVersion.wilx\n");
                if (!a.equals(context.getString(R.string.dataSideVersion))) {
                    AppLog.content.append("+ Old AppDataSide\n");
                    AppLog.content.append("+ Begin create new AppDataSide\n");
                    wilx.android.wtb.DataClone.AppDataSide b = new wilx.android.wtb.DataClone.AppDataSide();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End create new AppDataSide\n");
                    Toast.makeText(context, "Old AppDataSide!", Toast.LENGTH_SHORT).show();
                } else {
                    AppLog.content.append("+ Begin read AppDataSide.wilx\n");
                    fi = context.openFileInput("AppDataSide.wilx");
                    oi = new ObjectInputStream(fi);
                    wilx.android.wtb.DataClone.AppDataSide b = (wilx.android.wtb.DataClone.AppDataSide) oi.readObject();
                    oi.close();
                    fi.close();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End read AppDataSide.wilx\n");
                }
            }
            catch (Exception e) {
                AppLog.content.append("+ Error occurs\n");
                AppLog.content.append("+ Begin create new AppDataSide\n");
                wilx.android.wtb.DataClone.AppDataSide b = new wilx.android.wtb.DataClone.AppDataSide();

                Misc.cloneFrom(b);
                AppLog.content.append("+ End create new AppDataSide\n");
            }
            AppLog.content.append("------------------------------------------------------------\n");
        }

        public static void loadAppSettings(Context context) {
            AppLog.content.append("File: AppResources\nFunction: loadAppSettings\n");
            try
            {
                AppLog.content.append("+ Begin read settingsVersion.wilx\n");
                FileInputStream fi = context.openFileInput("settingsVersion.wilx");
                ObjectInputStream oi = new ObjectInputStream(fi);
                String a = (String) oi.readObject();
                oi.close();
                fi.close();
                AppLog.content.append("+ End read settingsVersion.wilx\n");
                if (!a.equals(context.getString(R.string.settingsVersion))) {
                    AppLog.content.append("+ Old Settings\n");
                    AppLog.content.append("+ Begin create new AppSettings\n");
                    wilx.android.wtb.DataClone.AppSettings b = new wilx.android.wtb.DataClone.AppSettings();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End create new AppSettings\n");
                    Toast.makeText(context, "Old settings!", Toast.LENGTH_SHORT).show();
                } else {
                    AppLog.content.append("+ Begin read AppSettings.wilx\n");
                    fi = context.openFileInput("AppSettings.wilx");
                    oi = new ObjectInputStream(fi);
                    wilx.android.wtb.DataClone.AppSettings b = (wilx.android.wtb.DataClone.AppSettings) oi.readObject();
                    oi.close();
                    fi.close();

                    Misc.cloneFrom(b);
                    AppLog.content.append("+ End read AppSettings.wilx\n");
                }
            }
            catch (Exception e) {
                AppLog.content.append("+ Error occurs\n");
                AppLog.content.append("+ Begin create new AppSettings\n");
                wilx.android.wtb.DataClone.AppSettings b = new wilx.android.wtb.DataClone.AppSettings();

                Misc.cloneFrom(b);
                AppLog.content.append("+ End create new AppSettings\n");
            }
            AppLog.content.append("------------------------------------------------------------\n");
        }

        public static void saveAppDataCore(Context context) {
            try
            {
                FileOutputStream fo = context.openFileOutput("dataCoreVersion.wilx", Context.MODE_PRIVATE);
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(context.getString(R.string.dataCoreVersion));
                oo.close();
                fo.close();

                fo = context.openFileOutput("AppDataCore.wilx", Context.MODE_PRIVATE);
                oo = new ObjectOutputStream(fo);
                wilx.android.wtb.DataClone.AppDataCore a = new wilx.android.wtb.DataClone.AppDataCore();
                Misc.cloneTo(a);
                oo.writeObject(a);
                oo.close();
                fo.close();
            }
            catch (Exception e) {   }
        }

        public static void saveAppDataSide(Context context) {
            try
            {
                FileOutputStream fo = context.openFileOutput("dataSideVersion.wilx", Context.MODE_PRIVATE);
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(context.getString(R.string.dataSideVersion));
                oo.close();
                fo.close();

                fo = context.openFileOutput("AppDataSide.wilx", Context.MODE_PRIVATE);
                oo = new ObjectOutputStream(fo);
                wilx.android.wtb.DataClone.AppDataSide a = new wilx.android.wtb.DataClone.AppDataSide();
                Misc.cloneTo(a);
                oo.writeObject(a);
                oo.close();
                fo.close();
            }
            catch (Exception e) {   }
        }

        public static void saveAppSettings(Context context) {
            try
            {
                FileOutputStream fo = context.openFileOutput("settingsVersion.wilx", Context.MODE_PRIVATE);
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(context.getString(R.string.settingsVersion));
                oo.close();
                fo.close();

                fo = context.openFileOutput("AppSettings.wilx", Context.MODE_PRIVATE);
                oo = new ObjectOutputStream(fo);
                wilx.android.wtb.DataClone.AppSettings a = new wilx.android.wtb.DataClone.AppSettings();
                Misc.cloneTo(a);
                oo.writeObject(a);
                oo.close();
                fo.close();
            }
            catch (Exception e) {   }
        }

        public static void saveAppLog(Context context) {
            try
            {
                FileOutputStream fo = context.openFileOutput("logVersion.wilx", Context.MODE_PRIVATE);
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(context.getString(R.string.logVersion));
                oo.close();
                fo.close();

                fo = context.openFileOutput("AppLog.wilx", Context.MODE_PRIVATE);
                oo = new ObjectOutputStream(fo);
                wilx.android.wtb.DataClone.AppLog a = new wilx.android.wtb.DataClone.AppLog();
                Misc.cloneTo(a);
                oo.writeObject(a);
                oo.close();
                fo.close();
            }
            catch (Exception e) {   }
        }

        public static void cloneFrom(wilx.android.wtb.DataClone.AppDataCore sources) {
            AppDataCore.semesters = sources.semesters;
            AppDataCore.maxColumn = sources.maxColumn;
            AppDataCore.maxRow = sources.maxRow;
        }

        public static void cloneFrom(wilx.android.wtb.DataClone.AppDataSide sources) {
            AppDataSide.currentSemester = sources.currentSemester;
            AppDataSide.currentWeek = sources.currentWeek;
        }

        public static void cloneFrom(wilx.android.wtb.DataClone.AppSettings sources) {
            AppSettings.gridLineColor = sources.gridLineColor;
            AppSettings.isDeveloping = sources.isDeveloping;
        }

        public static void cloneFrom(wilx.android.wtb.DataClone.AppLog sources) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
            Date d = Calendar.getInstance().getTime();
            String s = simpleDateFormat.format(d);
            s = "------------ " + s + " ------------\n";
            AppLog.content.insert(0, s);
            if (sources.content != null)
                AppLog.content.insert(0, sources.content);
        }

        public static void cloneTo(wilx.android.wtb.DataClone.AppDataCore destination) {
            destination.semesters = AppDataCore.semesters;
            destination.maxColumn = AppDataCore.maxColumn;
            destination.maxRow = AppDataCore.maxRow;
        }

        public static void cloneTo(wilx.android.wtb.DataClone.AppDataSide destination) {
            destination.currentSemester = AppDataSide.currentSemester;
            destination.currentWeek = AppDataSide.currentWeek;
        }

        public static void cloneTo(wilx.android.wtb.DataClone.AppSettings destination) {
            destination.gridLineColor = AppSettings.gridLineColor;
            destination.isDeveloping = AppSettings.isDeveloping;
        }

        public static void cloneTo(wilx.android.wtb.DataClone.AppLog destination) {
            destination.content = AppLog.content.toString();
        }
    }
}
