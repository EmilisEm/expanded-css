package testUtils;

import cssParser.sym;
import java_cup.runtime.Symbol;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AnswerReader extends BufferedReader {

    public AnswerReader(Reader in) {
        super(in);
    }

    public Symbol readNextSymbol() throws IOException {
        String input = this.readLine();
        List<String> line;

        if (input.contains("\"")) {
            String[] words = input.split("\"");
            words[1] = "\"%s\"".formatted(words[1]);

            line = Stream.of(words).map(String::trim).toList();
        } else {
            line = Arrays.stream(input.split(" ")).toList();
        }


        return switch (line.size()) {
            case 1 -> new Symbol(parseSym(line.getFirst()));
            case 2 -> new Symbol(parseSym(line.getFirst()), line.get(1));
            default -> throw new IOException();
        };
    }

    // Nepakankamai rupi normaliai exception'us sugaudyti. Jei laiko turesiu gal
    private static int parseSym(String text) {
        Field[] fields = sym.class.getFields();
        for (Field field : fields) {
            if (field.getName().equals(text)) {
                try {
                    return field.getInt(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new IllegalArgumentException("invalid sym class. No fields");
    }
}
