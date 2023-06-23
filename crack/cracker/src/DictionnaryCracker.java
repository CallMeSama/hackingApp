package crack.cracker.src;


import crack.src.Crack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionnaryCracker implements Crack {

    private String input;

    public DictionnaryCracker(String input) {
        this.input = input;
    }

    @Override
    public String getResult() {

        String dicName = "dictionnary.txt";

        try (FileReader fileReader = new FileReader(dicName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            boolean foundLine = false;
            while ((line = bufferedReader.readLine()) != null && !foundLine) {
                foundLine = line.equals(input);
            }

            bufferedReader.close();
            return foundLine ? "Found" : "not Found";

        } catch (IOException e) {
            return "File not found: " + dicName;
        }
    }
}
