---------------------------------------------------------
   How to run:

        cd into project folder
        mvn test

        OR

        You can also run the in the IDE from com.acme.cafe.service.CashierService.scala - > "Run CashierService"

---------------------------------------------------------
Requirements:
        Cafe ACME menu consists of the following items:
        Cola - Cold - 50p
        Coffee - Hot - £1.00
        Cheese Sandwich - Cold - £2.00
        Steak Sandwich - Hot - £4.50

        Customers don’t know how much to tip and staff need tips to survive!

        Standard Bill:
        Pass in a list of purchased items that produces a total bill.

        e.g. [“Cola”, “Coffee”, “Cheese Sandwich”] returns 3.5

        When all purchased items are drinks no service charge is applied
        When purchased items include any food apply a service charge of 10% to the total bill (rounded to 2 decimal places)
        When purchased items include any hot food apply a service charge of 20% to the total bill with a maximum £20 service charge

Assumptions:
    Have taken the liberty to assume that food and drink may be HOT or COLD. Hence the Product
    constructor requires that you specify the temperature

The entry point is CustomerBill.addCartProducts with signature:

    public void addCartProducts(List<CartProduct> cartProducts)

    System Requirements:
        Java 1.8
        Apache Maven

    Building:
    Install Java 8
        http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

    Install maven
        https://maven.apache.org/


Sample Receipt:

.----------------.  .----------------.  .----------------.  .----------------.
| .--------------. || .--------------. || .--------------. || .--------------. |
| |      __      | || |     ______   | || | ____    ____ | || |  _________   | |
| |     /  \     | || |   .' ___  |  | || ||_   \  /   _|| || | |_   ___  |  | |
| |    / /\ \    | || |  / .'   \_|  | || |  |   \/   |  | || |   | |_  \_|  | |
| |   / ____ \   | || |  | |         | || |  | |\  /| |  | || |   |  _|  _   | |
| | _/ /    \ \_ | || |  \ `.___.'\  | || | _| |_\/_| |_ | || |  _| |___/ |  | |
| ||____|  |____|| || |   `._____.'  | || ||_____||_____|| || | |_________|  | |
| |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' |
'----------------'  '----------------'  '----------------'  '----------------'



    100 Peachtree Road
    London, UK, SW1 09H7
    0984 3993 9993
    http://www.acme.com

    Fri Feb 03 03:57:59 GMT 2017               Server: John

    Table: 6       Cust Num : 9

            Cold Coffee @ 1.20 each x 2 : 2.40
            Hot Sandwich @ 2.30 each x 2 : 4.60

    Sub Total     :  7.00
    Service Charge:  1.40
    Total         :  8.40


That you for your custom