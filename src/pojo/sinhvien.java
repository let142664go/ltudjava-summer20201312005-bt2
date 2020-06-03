package pojo;

public class sinhvien implements java.io.Serializable {
    private String ma;
    private String ten;
    private String gioi_tinh;
    private String cmnd;
    public sinhvien(){

    }
    public sinhvien(String ma, String ten, String gioi_tinh, String cmnd){
        this.ma = ma;
        this.ten = ten;
        this.gioi_tinh = gioi_tinh;
        this.cmnd = cmnd;
    }
}