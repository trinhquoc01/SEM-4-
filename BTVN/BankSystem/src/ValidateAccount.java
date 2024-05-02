public class ValidateAccount {
    public final String MOBILE_NUMBER ="1";
    public final String PASSWORD ="1";

    public boolean checkAcount(String phone, String password) {
        if (phone.equals(MOBILE_NUMBER) ){
            if (password.equals(PASSWORD)) {
                System.out.println("Login successfully");
                return true;
            } else {
                System.out.println("Incorrect password");
                return false;
            }
        }else{
            System.out.println("Check your phone number again ");
            return false;
        }
    }

}