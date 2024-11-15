import java.io.*;
import java.util.*;

public class FixLogToCsv {
        // Function to parse a single FIX message into a Map of tag-value pairs
        public static Map<String, String> parseFixMessage(String fixMessage) {
            Map<String, String> fixMap = new HashMap<>();

            // Split the FIX message by the delimiter (SOH, represented by '\u0001' in FIX)
            String[] tagValuePairs = fixMessage.split("\\u0001"); // SOH is ASCII 0x01

            // Iterate through each tag-value pair and store them in the map
            for (String tagValue : tagValuePairs) {
                if (tagValue.contains("=")) {
                    String[] parts = tagValue.split("=", 2);
                    if (parts.length == 2) {
                        fixMap.put(parts[0], parts[1]);
                    }
                }
            }

            return fixMap;
        }

        // Function to write parsed FIX messages to a CSV file
        public static void writeFixToCsv(List<String> fixMessages, String csvFilename) throws IOException {
            List<Map<String, String>> parsedMessages = new ArrayList<>();
            Set<String> uniqueTags = new TreeSet<>();  // TreeSet keeps tags sorted and unique

            // Parse each FIX message and collect all unique tags
            for (String message : fixMessages) {
                Map<String, String> parsedMessage = parseFixMessage(message);
                parsedMessages.add(parsedMessage);
                uniqueTags.addAll(parsedMessage.keySet());
            }

            // Write to CSV file
            try (FileWriter csvWriter = new FileWriter(csvFilename)) {
                // Write the header (tags)
                csvWriter.append(String.join(",", uniqueTags));
                csvWriter.append("\n");

                // Write each FIX message as a row in the CSV
                for (Map<String, String> parsedMessage : parsedMessages) {
                    List<String> row = new ArrayList<>();
                    for (String tag : uniqueTags) {
                        row.add(parsedMessage.getOrDefault(tag, ""));  // Write empty string if tag not present
                    }
                    csvWriter.append(String.join(",", row));
                    csvWriter.append("\n");
                }

                System.out.println("CSV file '" + csvFilename + "' created successfully.");
            }
        }

        // Function to read a log file containing FIX messages
        public static List<String> readFixMessagesFromFile(String logFilename) throws IOException {
            List<String> fixMessages = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(logFilename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fixMessages.add(line.trim());
                }
            }

            return fixMessages;
        }

        // Example usage
        public static void main(String[] args) {
            // Input FIX log file containing raw FIX messages with ^A delimiter
            String logFilename = "fix_logs.txt";  // Replace with your input file path

            // Output CSV filename
            String csvFilename = "fix_messages.csv";

            try {
                // Read the raw FIX messages from the log file
                List<String> fixMessages = readFixMessagesFromFile(logFilename);

                // Convert and write the FIX messages to a CSV file
                writeFixToCsv(fixMessages, csvFilename);
            } catch (IOException e) {
                System.err.println("Error processing the log file: " + e.getMessage());
            }
        }
    }

#How to compile this - javac FixLogToCsv.java
        #How to run this - java FixLogToCsv
