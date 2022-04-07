import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Account {
    private String filename;
    private String password;
    private String username;
    private String userType;
    private ArrayList<String[]> accounts;
    private ArrayList<String[]> teachers;
    private ArrayList<String[]> students;

    public Account(String filename, String password,
                   String username, String userType) {
        this.filename = filename;
        this.password = password;
        this.username = username;
        this.userType = userType;
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

    public void deleteAccount(String filename, String username,
                              String password, String userType)
            throws IOException, InvalidAccountException {
        File f = new File("Accounts.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        int counter = 0;
        String accountLine = bfr.readLine();
        boolean accountExists = false;
        while (!(accountLine == null)) {
            String[] userInfo = accountLine.split(",");
            if (userInfo[0].equals(username) &&
                    userInfo[1].equals(password) &&
                    userInfo[2].equals(userType)) {
                accountExists = true;
                accounts.remove(counter);
                break;
            }
            accountLine = bfr.readLine();
            counter++;
        }
        if (accountExists == false){
            throw new InvalidAccountException();
        }
        FileOutputStream fos = new FileOutputStream("Accounts.txt", false);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < accounts.size(); i++) {
            pw.println(accounts.get(i)[0] + "," +
                    accounts.get(i)[1] + "," + accounts.get(i)[2]);
        }
        pw.close();
    }

    public void editAccount(String filename, String originalUser, String newUser,
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
}