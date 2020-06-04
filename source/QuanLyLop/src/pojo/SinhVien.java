package pojo;
import java.io.Serializable;

public class SinhVien implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String ma;
    private String ten;
    private String gioi_tinh;
    private String cmnd;
    public SinhVien(){

    }
    public SinhVien(String ma, String ten, String gioi_tinh, String cmnd){
        this.ma = ma;
        this.ten = ten;
        this.gioi_tinh = gioi_tinh;
        this.cmnd = cmnd;
    }
    public String getMa(){
        return this.ma;
    }
    public void setMa(String ma){
        this.ma = ma;
    }
    public String getTen(){
        return this.ten;
    }
    public void setTen(String ten){
        this.ten = ten;
    }
    public String getGioiTinh(){
        return this.gioi_tinh;
    }
    public void setGioiTinh(String gioi_tinh){
        this.gioi_tinh = gioi_tinh;
    }
    public String getCMND(){
        return this.cmnd;
    }
    public void setCMND(String cmnd){
        this.cmnd = cmnd;
    }
}
