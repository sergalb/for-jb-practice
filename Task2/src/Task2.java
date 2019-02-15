import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public static void main(String[] args) {
        int n;
        Map<String, Integer> names = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            try (BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
                for (int i = 0; i < n; i++) {
                    String cur_name = reader.readLine();
                    Integer count = names.remove(cur_name);
                    if (count == null) {
                        names.put(cur_name, 0);
                        writer.write("OK");
                    } else {
                        names.put(cur_name, count + 1);
                        writer.write(cur_name + (count + 1));
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
