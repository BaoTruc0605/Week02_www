package vn.edu.iuh.fit.week02.demo;

import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.models.ProductPrice;
import vn.edu.iuh.fit.week02.repository.EmployeeRepository;
import vn.edu.iuh.fit.week02.repository.ProductPriceRepository;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;

import java.time.LocalDate;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        employeeRepository.insert(new Employee("Trần Bảo Trúc", LocalDate.now(),"baotruc@gmail.com","0338030540","Nguyễn Thái Sơn, Phuường 4, Gò Vấp, HCM", EmployeeStatus.TamNghi));


        ProductPriceRepository productPriceRepository= new ProductPriceRepository();
        for(ProductPrice p : productPriceRepository.getPriceHieuLuc()){
            System.out.println(p.toString() +"\n");
        }


//        ProductPriceRepository productPriceRepository= new ProductPriceRepository();
//        List<ProductPrice> pList = productPriceRepository.findByProductID(4);
//        for(ProductPrice p : productPriceRepository.findByProductID(4)){
////            System.out.println(p.toString() +"\n");
//            if(pList.contains(p)){
//                System.out.println( "1\n");
//            }
//            else{
//                System.out.println( "2\n");
//            }
//        }
    }
}
