import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LogToCSVConverter {

    public static void main(String[] args) {
        String inputFilePath = "path/to/your/logfile.txt"; // Change to your input log file path
        String outputFilePath = "path/to/your/outputfile.csv"; // Change to your desired output file path

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            List<String> header = new ArrayList<>();
            List<Map<String, String>> records = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|"); // Split by pipe delimiter

                Map<String, String> record = new HashMap<>();
                for (String field : fields) {
                    String[] tagValuePair = field.split("=");
                    if (tagValuePair.length == 2) {
                        String tag = tagValuePair[0].trim();
                        String value = tagValuePair[1].trim();
                        record.put(tag, value);
                        // Add tag to header if not already present
                        if (!header.contains(tag)) {
                            header.add(tag);
                        }
                    }
                }
                records.add(record);
            }

            // Write the header
            writer.write(String.join(",", header));
            writer.newLine();

            // Write the records
            for (Map<String, String> record : records) {
                List<String> row = new ArrayList<>();
                for (String tag : header) {
                    row.add(record.getOrDefault(tag, "")); // Default to empty string if no value exists
                }
                writer.write(String.join(",", row));
                writer.newLine();
            }

            System.out.println("CSV file created successfully at: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
