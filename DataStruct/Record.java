package wilx.android.wtb.DataStruct;

import java.io.Serializable;

/**
 * Created by william on 20/11/2017.
 */

public class Record implements Serializable
{

    public String maMonHoc;
    public String tenMonHoc;
    public double tinChi;
    public double tinChiHocPhi;
    public double mucHocPhi;
    public String nhomTo;
    public int thu;
    public boolean[] tiet;
    public String phongHoc;
    public boolean[] tuanHoc;
    public String where;

    public String fillText;

    public Record(String maMonHoc, String tenMonHoc, double tinChi,
                  double tinChiHocPhi, double mucHocPhi, String nhomTo,
                  int thu, boolean[] tiet, String phongHoc, boolean[] tuanHoc, String where) {
        this.maMonHoc = new String(maMonHoc);
        this.tenMonHoc = new String(tenMonHoc);
        this.tinChi = tinChi;
        this.tinChiHocPhi = tinChiHocPhi;
        this.mucHocPhi = mucHocPhi;
        this.nhomTo = new String(nhomTo);
        this.thu = thu;
        this.tiet = new boolean[17];
        for (int i=0; i<17; ++i)
            this.tiet[i] = tiet[i];
        this.phongHoc = new String(phongHoc);
        this.tuanHoc = new boolean[53];
        for (int i=0; i<53; ++i)
            this.tuanHoc[i] = tuanHoc[i];
        this.where = new String(where);

        String a1 = new String("Mã môn học : " + maMonHoc);
        String a2 = new String("Tên môn học : " + tenMonHoc);
        String a3 = new String("Phòng học : " + phongHoc);
        String a4 = new String("Nhóm tổ : " + nhomTo);
        String a5 = new String("Tín chỉ : " + Double.toString(tinChi));
        String a6 = new String("Tín chỉ học phí : " + Double.toString(tinChiHocPhi));
        String a7 = new String("Mức học phí: " + Double.toString(mucHocPhi));
        String a8;
        if (thu != 6)
            a8 = "Thứ : " + Integer.toString(thu + 2);
        else
            a8 = "Thứ : " + "CN";
        String a9 = "Tiết : ";
        for (int i=0; i<17; ++i)
            if (tiet[i])
                a9 = new String(a9 + Integer.toString(i + 1) + ", ");
        a9 = new String(a9.substring(0, a9.length() - 2));
        String a10 = "Tuần : ";
        for (int i=0; i<53; ++i)
            if (tuanHoc[i])
                a10 = new String(a10 + Integer.toString(i + 1) + ", ");
        a10 = new String(a10.substring(0, a10.length() - 2));
        String a11 = new String("Nơi học : " + where);
        String a = new String(a1 + "\n" + a2 + "\n" + a3 + "\n" + a4 + "\n" + a5 + "\n" + a6 + "\n" + a7 + "\n" + a8 + "\n" + a9 + "\n" + a10 + "\n" + a11);
        this.fillText = a;
    }


}
