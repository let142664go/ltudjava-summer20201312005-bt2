package pojo;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String ma;
    private String mk;
    private String lisc;

    public TaiKhoan() {

    }

    public TaiKhoan(String ma, String ten, String gioi_tinh, String cmnd) {
        this.ma = ma;
        this.mk = ten;
        this.lisc = gioi_tinh;
    }

    public String getMa() {
        return this.ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getPass() {
        return this.mk;
    }

    public void setPass(String mk) {
        this.mk = mk;
    }

    public String getLicsen() {
        return this.lisc;
    }

    public void setLicsen(String lisc) {
        this.lisc = lisc;
    }
}
