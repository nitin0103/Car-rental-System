import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class car{
    private String carId;
    private String Brand;
    private String Model;
    private double BasePricePerDay;
    private boolean isAvailabel;

    public car(String carId ,String Brand,String Model,double BasePricePerDay,boolean isAvailabel){
        this.carId=carId;
        this.Brand=Brand;
        this.Model=Model;
        this.BasePricePerDay=BasePricePerDay;
        this.isAvailabel=true;
    }//methods
   public car(String string, String string2, String string3, String string4) {
        //TODO Auto-generated constructor stub
    }
public String getcarId(){
    return carId;
   }
   public String getBrand(){
    return Brand;
   }
   public String getModel(){
    return Model;
   }
   public double calculatePrice(int rentalDays){
    return BasePricePerDay * rentalDays;
   }
   public boolean isAvailabel(){
    return isAvailabel;
   }
   public void rent(){
    isAvailabel=false;
   }
   public void returnCar(){
    isAvailabel=true;
   }
}
class Customer{
     private String customerId;
     private String name;
     
     public Customer(String customerId,String name){
        this.customerId= customerId;
        this.name=name;
     }
     public String getcustomerID(){
        return customerId;
     }
     public String getname(){
        return name;
}
}
class Rental{
    private car car; // class is also datatype
    private Customer customer;
    private int days;

    public Rental( car car,Customer customer,int days){
        this.car=car;
        this.customer=customer;
        this.days=days;
    }
    public car getCar(){
        return car;
    }
    public  Customer getCustomer(){
        return customer;
    }
    public int getDays(){
        return days;
    }

}
class carRentalSystem{// array list is used in this because of size of list is atmtcly doubled
    private List<car> cars;
    private List<Customer>customers;
    private List<Rental>rentals;

    public carRentalSystem(){//creation of array list..n stored in memory
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();

    }
     public void addCar(car car){
        cars.add(car);

     }
     public void addCustomer(Customer customer){
        customers.add(customer);
     }
     public void rentCar(car car,Customer customer,int days){
        if(car.isAvailabel()){
            car.rent();
            rentals.add(new Rental(car, customer, days));
        }else{
            System.out.println("Car is not available for Rent");
        }
     }
     public void returnCar(car car){
        car.returnCar();
        Rental rentaltoRemove=null;
        for(Rental rental:rentals){
            if(rental.getCar()==car){
                rentaltoRemove=rental;
                break;
            }
        }
        if(rentaltoRemove!=null){
            rentals.remove(rentaltoRemove);//remove is method in arraylist
            System.out.println("Car Returned Successfully");
        }else{
            System.out.println("Car was not rented");
        }
     }
     public void menu(){
        Scanner scanner=new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1.Rent a car ");
            System.out.println("2.Return a car");
            System.out.println("3.Exit");
            System.out.println("Enter Your choice:");

            int choice=scanner.nextInt();
            scanner.nextLine();
         
            if(choice==1){
                System.out.println("\n== Rent a car ==\n");
                System.out.println("Enter your Name:");
                String customerName=scanner.nextLine();

                System.out.println("\n available cars:");
                for(car car:cars){
                    if(car.isAvailabel()){
                        System.out.println(car.getcarId()+" - "+car.getBrand()+" - "+car.getModel());
                    }
                }
                System.out.println("\n Enter the car ID you want to rent:");
                String carId=scanner.nextLine();

                System.out.println("Enter the number of days for rental:");
                int rentalDays=scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer=new Customer("CUS"+(customers.size()+1),customerName);
                addCustomer(newCustomer);

                car selectedCar=null;
                for(car car:cars){
                   if(car.getcarId().equals(carId)&& car.isAvailabel()){
                    selectedCar=car;
                    break;

                   }
                }
                if(selectedCar!=null){
                    double totalprice=selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n == Rental information ==\n");
                    System.out.println("Customer ID:"+newCustomer.getcustomerID());
                    System.out.println("car"+selectedCar.getBrand()+ " "+selectedCar.getModel());
                    System.out.println("Rental Days:"+rentalDays);
                    System.out.printf("Total price:$%.2f%n",totalprice);

                    System.out.println("\n confirm rental(Y/N)");
                    String confirm=scanner.nextLine();

                    if(confirm.equalsIgnoreCase( "Y")){//EIC it does not check in which case
                        rentCar(selectedCar,newCustomer,rentalDays);  
                         System.out.println("\n car rented successfully:");
                    } else{
                        System.out.println("\n rental canceled");
                    }

                }else{
                    System.out.println("\n Invalid car selection or car not availabel for rent");
                }
            } else if(choice==2){
                System.out.println("\n== Return a car ==\n");
                System.out.println("Enter the car id you want to return :");
                String carID=scanner.nextLine();

                car carToReturn =null;
                for(car car:cars){
                    if(car.getcarId().equals(carID)&& !car.isAvailabel()){
                        carToReturn=car;
                        break;
                    }
                }
                if (carToReturn !=null){
                    Customer customer=null;
                    for(Rental rental:rentals){
                        if(rental.getCar()==carToReturn){
                            customer=rental.getCustomer();
                            break;
                        }
                    }
                    if(customer!=null){
                        returnCar(carToReturn);//method call kiya
                        System.out.println("car returned successfully by "+customer.getname());
                    }else{
                        System.out.println("car was not rented or rental information is missing ");
                    }
                }else{
                    System.out.println("Invalid car ID or car is not rented");
                }
                } else if(choice==3){
                    break;
                
                }else{
                    System.out.println("Invalid choice.please enter a valid option ");
                }
                
            }
            System.out.println("\n Thank You for using the Car Rental System");
        }
     }
     public class main {
     
        public static void main(String[] args) {
            carRentalSystem rentalSystem=new carRentalSystem();

            car car1= new car("101", "TOYOTA", "CAMRY", 50.0, false);
            car car2= new car("102", "MAHINDRA", "Thar", 150.0, false);
            car car3= new car("103", "TOYOTA", "Innova", 200.0, false);

            rentalSystem.addCar(car1);
            rentalSystem.addCar(car2);
            rentalSystem.addCar(car3);

            rentalSystem.menu();
        }
     }