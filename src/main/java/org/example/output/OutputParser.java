package org.example.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OutputParser {
    public static void write(List<Optional<BigDecimal>> list, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Optional<BigDecimal> optional : list) {
                if (optional.isPresent()) {
                    writer.write(optional.get().toPlainString()+"x");
                } else {
                    writer.write("Wrong input");
                }
                writer.write("\n");
            }
        }
    }
}