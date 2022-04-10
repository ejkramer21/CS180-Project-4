import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        return this.userType;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword(){
        return this.password;
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

    public void deleteAccount(String username,
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
            //I add a message string because it was giving an error.  Feel free to change it -Joy
            throw new InvalidAccountException("This account doesn't exist!");
        }
        FileOutputStream fos = new FileOutputStream("Accounts.txt", false);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < accounts.size(); i++) {
            pw.println(accounts.get(i)[0] + "," +
                    accounts.get(i)[1] + "," + accounts.get(i)[2]);
        }
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
        for (String[] account : this.accounts) {
            if (account[2].equals("1")) {
                this.students.add(account);
            } else {
                this.teachers.add(account);
            }
        }
    }

    public void accountsArrayListAfterReset() throws FileNotFoundException {
        try {
            File f = new File("Accounts.txt");
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

    /*public static void main(String[] args) throws IOException, InvalidAccountException {
        Account yes = new Account("user", "pass", "1");
        yes.createAccount("user", "pass", "1");
        yes.editAccount("user", "u","pass", "1");
        //yes.deleteAccount("user", "pass", "1");
    }*/
}