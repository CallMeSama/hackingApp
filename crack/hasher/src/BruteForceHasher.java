package crack.hasher.src;

import java.security.MessageDigest;

import crack.src.Crack;

public class BruteForceHasher implements Crack {
    private String input;
    private String output = "Not Found";
    long start, end;
    // String[] combinations = generateCombos();

    public BruteForceHasher(String input) {
        this.input = input;

    }

    /*
     * public static String[] generateCombos() {
     * List<String> combosList = new ArrayList<>();
     * char[] letters = "passer".toCharArray();
     * 
     * // Générer toutes les combinaisons possibles de 5 lettres
     * for (char char1 : letters) {
     * for (char char2 : letters) {
     * for (char char3 : letters) {
     * for (char char4 : letters) {
     * for (char char5 : letters) {
     * String combination = "" + char1 + char2 + char3 + char4 + char5;
     * combosList.add(combination);
     * }
     * }
     * }
     * }
     * }
     * String[] combos = new String[combosList.size()];
     * combos = combosList.toArray(combos);
     * return combos;
     * }
     */

    public boolean generateCombinations(String prefix, int length) {

        if (length == 0) {
            return hasher(prefix).equals(input);
        } else {
            for (char c = 'a'; c <= 'z'; c++) {
                if (generateCombinations(prefix + c, length - 1)) {
                    System.out.println("hey");
                    return true;
                }
            }
        }

        return false;
    }

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
        start = System.nanoTime();
        if (generateCombinations("", 5)) {
            return "Found";
        } else
            return "No Found";
    }

    // public String toString() {
    // if (trouve) {
    // return "Password Found";
    // } else {
    // return "Password not Found";
    // }
    // }

}
