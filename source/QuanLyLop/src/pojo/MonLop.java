package pojo;

import java.io.Serializable;

public class MonLop implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ma_lop;
    private String ma_mon_hoc;
    private String ten_mon_hoc;
    private String phong_hoc;

    public MonLop() {

    }

    public MonLop(String lop, String mon, String tenlop, String phong) {
        this.ma_lop = lop;
        this.ma_mon_hoc = mon;
        this.ten_mon_hoc = tenlop;
        this.phong_hoc = phong;
    }

    public String getMaLop() {
        return this.ma_lop;
    }

    public void setMaLop(String ma) {
        this.ma_lop = ma;
    }

    public String getMaMonHoc() {
        return this.ma_mon_hoc;
    }

    public void setMaMonHoc(String mon) {
        this.ma_mon_hoc = mon;
    }

    public String getTenMonHoc() {
        return this.ten_mon_hoc;
    }

    public void setTenMonHoc(String tenmon) {
        this.ten_mon_hoc = tenmon;
    }

    public String getPhongHoc() {
        return this.phong_hoc;
    }

    public void setPhongHoc(String phong) {
        this.phong_hoc= phong;
    }
}