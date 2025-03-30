import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            Map<String, Integer> wordcount = readFile(args[0]);
            // output filename not specified in instructions so hardcoded to be "out.txt"
            writeFile("out.txt", wordcount);
        } else {
            System.out.println("You must provide a file input");
        }
    }


    // Read a file, count the occurances of each unique line, and return the HashMap of wordcounts
    public static Map<String, Integer> readFile(String filename) {
        Map<String, Integer> wordcount = new HashMap<>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                // Increment wordcount if it exists, else create dictionary element
                if (wordcount.containsKey(line)) {
                    int count = wordcount.get(line);
                    wordcount.put(line, count+1);
                } else {
                    wordcount.put(line, 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordcount;
    }


    public static void writeFile(String filename, Map<String, Integer> wordcount) {
        try {
            FileWriter fr = new FileWriter(filename);
            BufferedWriter writer = new BufferedWriter(fr);
            
            // Iterate through map and write lines
            for (Map.Entry<String, Integer> entry : wordcount.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                writer.write(key + " " + value + "\n");
            }
            writer.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
