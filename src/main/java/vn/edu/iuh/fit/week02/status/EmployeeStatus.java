package vn.edu.iuh.fit.week02.status;

public enum EmployeeStatus {
    DangLamViec(1),
    TamNghi(0),
    NghiViec(-1);
    private int value;
    EmployeeStatus(int value){
        this.value = value;
    }



    public int getValue() {
        return this.value;
    }


}
