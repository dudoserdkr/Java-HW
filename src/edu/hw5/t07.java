package edu.hw5;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
import java.util.stream.*;

// Run with: java t07 <input-file> "<Surname Initials>"

public class t07 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java PhoneDirectory <input-file> \"<Surname Initials>\"");
            return;
        }
        Path input = Paths.get(args[0]);
        String targetEmployee = args[1].trim();

        List<Record> records = readRecords(input);
        List<String> phonesForEmployee = listPhonesForEmployee(records, targetEmployee);
        Map<String, Long> counts = countPhonesPerEmployee(records);

        writeLines(Paths.get("result_a.txt"), phonesForEmployee);
        writeLines(Paths.get("result_b.txt"), formatCounts(counts));
    }

    static class Record {
        final String employee;
        final String phone;
        Record(String employee, String phone) {
            this.employee = employee;
            this.phone = phone;
        }
    }

    static List<Record> readRecords(Path input) {
        try {
            List<String> lines = Files.readAllLines(input, StandardCharsets.UTF_8);
            List<Record> out = new ArrayList<>();
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;
                String[] parts = trimmed.split("\\s+", 3);
                if (parts.length < 3) continue;
                String employee = parts[0] + " " + parts[1];
                String phone = parts[2];
                out.add(new Record(employee, phone));
            }
            return out;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static List<String> listPhonesForEmployee(List<Record> records, String employee) {
        return records.stream()
                .filter(r -> r.employee.equalsIgnoreCase(employee))
                .map(r -> r.phone)
                .collect(Collectors.toList());
    }

    static Map<String, Long> countPhonesPerEmployee(List<Record> records) {
        LinkedHashMap<String, Long> result = new LinkedHashMap<>();
        for (Record r : records) {
            result.put(r.employee, result.getOrDefault(r.employee, 0L) + 1L);
        }
        return result;
    }

    static List<String> formatCounts(Map<String, Long> counts) {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, Long> e : counts.entrySet()) {
            lines.add(e.getKey() + " ; " + e.getValue());
        }
        return lines;
    }

    static void writeLines(Path out, List<String> lines) {
        try {
            Files.write(out, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
