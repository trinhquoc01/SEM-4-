import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static long balanceNumber = 10000000;
    List<TransactionHistory> list = new ArrayList<TransactionHistory>();

    public long getBalanceNumber(){
        return balanceNumber;
    }
    public long transfer(long number){
        if (number < 50000){
            System.out.println("Minimum amount 50000");
        }else if (number > balanceNumber) {
            System.out.println(" Insufficient balance !");
        }else {
            balanceNumber = balanceNumber -  number;
            System.out.println("Success");
        }
        return balanceNumber;
    }

    public void actionTransfer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the receiving account number : ");
        String number = scanner.nextLine();
        System.out.print("Enter the amount : ");
        long money = Long.parseLong(scanner.nextLine());
        System.out.print("Coment: ");
        String action = scanner.nextLine();
        LocalDate date = LocalDate.now();
        transfer(money);
        if (money >=50000 ){
            list.add(new TransactionHistory(IdGenerate.getNewId(),date,action,number,money ));
        }

    }

    public void getHistory(){
        for (TransactionHistory history : list){
            System.out.println(history);
        }
    }

    public static String formatMoney(long money){
        DecimalFormat formats = new DecimalFormat("###,###,##0.00");
        return formats.format(money);
    }


}