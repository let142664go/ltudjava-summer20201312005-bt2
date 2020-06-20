package pojo;

import java.io.Serializable;

public class Lop implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String ma_sinh_vien;
    private String ten_sinh_vien;
    private String ma_lop;
    private String dau;
    private Integer trang_thai;
    private Double diem_gk;
    private Double diem_ck;
    private Double diem_khac;
    private Double diem_tong;

    public Lop() {

    }

    public Lop(String ma_sinh_vien, String ten_sinh_vien, String ma_lop, Integer trang_thai, Double diem_gk, Double diem_ck,
            Double diem_khac, Double diem_tong, String dau) {
        this.ma_sinh_vien = ma_sinh_vien;
        this.ten_sinh_vien = ten_sinh_vien;
        this.ma_lop = ma_lop;
        this.trang_thai = trang_thai;
        this.diem_gk = diem_gk;
        this.diem_ck = diem_ck;
        this.diem_khac = diem_khac;
        this.diem_tong = diem_tong;
        this.dau = dau;
    }

    public String getDau() {
        return this.dau;
    }

    public void setDau(String ma) {
        this.dau = ma;
    }

    public String getMaSV() {
        return this.ma_sinh_vien;
    }

    public void setMaSV(String ma) {
        this.ma_sinh_vien = ma;
    }

    public String getTenSV() {
        return this.ten_sinh_vien;
    }

    public void setTenSV(String ten) {
        this.ten_sinh_vien = ten;
    }
    public String getMaLop() {
        return this.ma_lop;
    }

    public void setMaLop(String ma) {
        this.ma_lop = ma;
    }

    public int getTrangThai() {
        return this.trang_thai;
    }

    public void setTrangThai(int tt) {
        this.trang_thai = tt;
    }

    public Double getDiemGK() {
        return this.diem_gk;
    }

    public void setDiemGK(Double diem) {
        this.diem_gk = diem;
    }

    public Double getDiemCK() {
        return this.diem_ck;
    }

    public void setDiemCK(Double diem) {
        this.diem_ck = diem;
    }

    public Double getDiemKhac() {
        return this.diem_khac;
    }

    public void setDiemKhac(Double diem) {
        this.diem_khac = diem;
    }

    public Double getDiemTong() {
        return this.diem_tong;
    }

    public void setDiemTong(Double diem) {
        this.diem_tong = diem;
    }
}
