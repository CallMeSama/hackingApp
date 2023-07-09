package src.crack.hasher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import src.crack.factory.Crack;


public class DictionnaryHasher implements Crack {

    private String input;
    private String filePath = "dictionnary.txt";
    String[] passwordArray = readCSV(filePath);
    private boolean trouve = false;
    long start, end;

    public DictionnaryHasher(String input) {
        this.input = input;
    }

    // This method reads a csv file and store the words into an array
    public static String[] readCSV(String filePath) {
        List<String> passwordList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Adding the line into the list
                passwordList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Casting the list into an array
        String[] words = new String[passwordList.size()];
        words = passwordList.toArray(words);
        return words;
    }

    // This method return the hash of any word given in the parameters
    public static String hasher(String password) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes();

            digest.reset();
            digest.update(passwordBytes);
            byte[] message = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < message.length; i++) {
                hexString.append(Integer.toHexString(
                        0xFF & message[i]));
            }
            hash = hexString.toString();

        } catch (Exception e) {
        }
        return hash;

    }

    @Override
    public String getResult() {
        // This for loop compare the input to the hashes of the words in the
        // passwordList
        start = System.nanoTime();
        for (String password : passwordArray) {
            if (input.equals(hasher(password))) {
                trouve = true;
                end = System.nanoTime();
                System.out.println(input + " -->" + password);
                break;
            }
        }
        return toString();
        // throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }

    public String toString() {
        if (trouve) {
            long duration = end - start;
            duration = TimeUnit.NANOSECONDS.toMillis(duration);
            return "Password Found in " + duration + " Millisecondes";
        } else {
            return "Password not Found";
        }
    }

}
