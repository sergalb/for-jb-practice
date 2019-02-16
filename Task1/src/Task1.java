import java.io.*;
import java.util.*;


public class Task1 {
    private static List<Letter> alphabet = new ArrayList<>(26);
    private static class Letter implements Comparable<Letter>{
        private char value;
        private Set<Character> lesser = new HashSet<>();
        private Set<Character> larger = new HashSet<>();

        void addInLesser(Letter letter) throws ImpossibleException {
            if (lesser.contains(letter.value)) {
                return;
            }
            if (larger.contains(letter.value)) {
                throw new ImpossibleException();
            }
            for (char ch: larger) {
                Letter cur_larger = alphabet.get((ch - 'a'));
                letter.addInLarger(cur_larger);
                cur_larger.addInLesser(letter);
            }
            lesser.add(letter.value);
        }

        void addInLarger(Letter letter) throws ImpossibleException {
            if (larger.contains(letter.value)) {
                return;
            }
            if (lesser.contains(letter.value)) {
                throw new ImpossibleException();
            }
            for (char ch: lesser) {
                Letter cur_lesser = alphabet.get((ch - 'a'));
                letter.addInLesser(cur_lesser);
                cur_lesser.addInLarger(letter);
            }
            larger.add(letter.value);
        }
        Letter(char value) {
            this.value = value;
        }

        @Override
        public int compareTo(Letter other) {
            return Integer.compare(lesser.size(), other.lesser.size());
        }
    }
    private static void compareNames(String first, String second) throws ImpossibleException {
        int length = Math.min(first.length(), second.length());
        for (int i = 0; i < length; ++i) {
            if (first.charAt(i) != second.charAt(i)) {
                Letter min = alphabet.get(first.charAt(i) - 'a');
                Letter max = alphabet.get(second.charAt(i) - 'a');
                alphabet.get((min.value - 'a')).addInLarger(max);
                alphabet.get((max.value - 'a')).addInLesser(min);

            }
        }
        if (first.length() > second.length()) {
            throw new ImpossibleException();
        }
    }

    public static void main(String[] args) {
        int n;

        for (int i = 0; i < 26; ++i) {
            alphabet.add(new Letter((char)('a' + i)));
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
                n = Integer.parseInt(reader.readLine());
                String first_name, second_name;
                first_name = reader.readLine();
                for (int i = 1; i < n; i++) {
                    second_name = reader.readLine();
                    try {
                        compareNames(first_name, second_name);
                    } catch (ImpossibleException e1) {
                        writer.write("Impossible");
                        return;
                    }
                    first_name = second_name;
                }
                Collections.sort(alphabet);
                for (Letter letter : alphabet) {
                    writer.write(letter.value);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

