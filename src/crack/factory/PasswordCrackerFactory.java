package src.crack.factory;
import src.crack.cracker.BruteForceCracker;
import src.crack.cracker.DictionnaryCracker;
import src.crack.hasher.BruteForceHasher;
import src.crack.hasher.DictionnaryHasher;

public class PasswordCrackerFactory {
    public static Crack getInstance(String input, int choice) throws Exception {
        Crack crack = null;
        if (choice == 1) {
            crack = new BruteForceHasher(input);
        } else if (choice == 2) {
            crack = new DictionnaryHasher(input);
        } else if (choice == 3) {
            crack = new BruteForceCracker(input);
        } else if (choice == 4) {
            crack = new DictionnaryCracker(input);
        } else {
            throw new Exception();
        }

        return crack;
    }
}