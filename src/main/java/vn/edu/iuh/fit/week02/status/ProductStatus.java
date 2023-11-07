package vn.edu.iuh.fit.week02.status;
public enum ProductStatus {
    DangKinhDoanh(1),
    TamNgung(0),
    KhongKinhDoanh(-1);
    private int value;
    ProductStatus(int value){
        this.value = value;
    }


    public int getValue() {
        return this.value;
    }

}
