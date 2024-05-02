import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ValidateAccount validateAccount = new ValidateAccount();
        Controller controller = new Controller();
        boolean isCheck = false;
        while (!isCheck) {
            System.out.print("Enter phone : ");
            String mobile = sc.nextLine();

            System.out.print("Enter password : ");
            String password = sc.nextLine();
            isCheck = validateAccount.checkAcount(mobile, password);
        }
        while (true) {
            System.out.println("Enter your selection: ");
            System.out.println("1 : Check balance");
            System.out.println("2 : Transfer money");
            System.out.println("3 : Check transaction history");
            System.out.println("4 : EXit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Blance : ");
                    long balance = controller.getBalanceNumber();
                    System.out.println(Controller.formatMoney(balance));
                    break;
                case 2:
                    controller.actionTransfer();
                    break;
                case 3:
                    System.out.println("Transaction history: ");
                    controller.getHistory();
                    break;
                case 0:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inappropriate choice");
                    break;

            }
        }
    }


    public void menu(){

    }
}