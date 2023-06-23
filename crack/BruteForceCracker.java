package crack.cracker.src;
import crack.src.Crack;

public class BruteForceCracker implements Crack {

    private String input;

    public BruteForceCracker(String input) {
        this.input = input;
    }

    public boolean generateCombinations(String prefix, int length) {

        if (length == 0) {
            return prefix.equals(input);
        } else {
            for (char c = 'a'; c <= 'z'; c++) {
                if (generateCombinations(prefix + c, length - 1)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public String getResult() {
        if (generateCombinations("", 10))
            return "Found";
        else
            return "No Found";
    }

    public String toString() {
        return "BruteForceCracker";
    }
}
