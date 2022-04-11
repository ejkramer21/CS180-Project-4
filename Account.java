import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Account
 * An account object and management system. Allows for the creation, deletion, and editing of accounts.
 *
 * @author James Kor
 * @version April 10, 2022
 */
//I added some getters and setters -Joy

public class Account {
    private String password;
    private String username;
    private String userType;
    private ArrayList<String[]> accounts;
    private ArrayList<String[]> teachers;
    private ArrayList<String[]> students;

    public Account(String username, String password,
                   String userType) {
        this.password = password;
        this.username = username;
        this.userType = userType;
        this.accounts = new ArrayList<String[]>();
        this.teachers = new ArrayList<String[]>();
        this.students = new ArrayList<String[]>();
    }

    public String getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    public void accountsArrayListAfterReset() throws FileNotFoundException {
        try {
            File f = new File("Accounts.txt");
            f.createNewFile();
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = "";
            while((line = bfr.readLine()) != null){
                String[] accountArray = line.split(",");
                accounts.add(accountArray);
            }
        } catch (IOException e){
            throw new FileNotFoundException("No accounts have been made, accounts.txt does not exist.");
        }
    }

    public void createAccount(String username,
                              String password,
                              String userType) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("Accounts.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(username + "," + password + "," + userType);
        String[] userInfo = {username, password, userType};
        this.accounts.add(userInfo);
        pw.close();
    }


    public void editAccount(String originalUser, String newUser,
                            String newPassword, String diffUser)
            throws FileNotFoundException {
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i)[0].equals(originalUser)){
                this.accounts.remove(i);
                String[] replacement = {newUser, newPassword, diffUser};
                accounts.add(replacement);
            }
        }
        FileOutputStream fos = new FileOutputStream("Accounts.txt", false);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < accounts.size(); i++) {
            pw.println(accounts.get(i)[0] + "," +
                    accounts.get(i)[1] + "," + accounts.get(i)[2]);
        }
        pw.close();
    }

    public void sortIdentity() {
        students = new ArrayList<String[]>();
        teachers = new ArrayList<String[]>();
        for (String[] account : this.accounts) {
            if (account[2].equals("1")) {
                students.add(account);
            } else {
                teachers.add(account);
            }
        }
    }

    public static void main(String[] args) throws IOException, InvalidAccountException {
        Account yes = new Account("user", "pass", "1");
        yes.createAccount("user", "pass", "1");
        yes.editAccount("user", "u","pass", "1");
        //yes.deleteAccount("user", "pass", "1");
    }
}
