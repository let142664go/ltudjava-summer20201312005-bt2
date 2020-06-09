package pojo;

import java.io.Serializable;

public class Lop implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String ma_sinh_vien;
    private String ma_lop;
    private Integer trang_thai;
    private Float diem_gk;
    private Float diem_ck;
    private Float diem_khac;
    private Float diem_tong;

    Lop(){

    }

    public String getMaSV(){
        return this.ma_sinh_vien;
    }

    public void setMaSV(String ma){
        this.ma_sinh_vien = ma;
    }

    public String getMaLop(){
        return this.ma_lop;
    }
    
    public void setMaLop(String ma){
        this.ma_lop = ma;
    }

    public int getTrangThai(){
        return this.trang_thai;
    }
    
    public void setTrangThai(int tt){
        this.trang_thai = tt;
    }

    public Float getDiemGK(){
        return this.diem_gk;
    }
    
    public void setDiemGK(Float diem){
        this.diem_gk = diem;
    }

    public Float getDiemCK(){
        return this.diem_ck;
    }
    
    public void setDiemCK(Float diem){
        this.diem_ck = diem;
    }

    public Float getDiemKhac(){
        return this.diem_khac;
    }
    
    public void setDiemKhac(Float diem){
        this.diem_khac = diem;
    }

    public Float getDiemTong(){
        return this.diem_tong;
    }
    
    public void setDiemTong(Float diem){
        this.diem_tong = diem;
    }
}
