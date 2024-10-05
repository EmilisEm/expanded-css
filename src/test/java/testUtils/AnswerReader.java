package testUtils;

import cssParser.sym;
import java_cup.runtime.Symbol;

import java.io.*;
import java.lang.reflect.Field;

public class AnswerReader extends BufferedReader {

    public AnswerReader(Reader in) {
        super(in);
    }

    public Symbol readNextSymbol() throws IOException {
        String[] line = this.readLine().split(" ");

        return switch (line.length) {
            case 1 -> new Symbol(parseSym(line[0]));
            case 2 -> new Symbol(parseSym(line[0]), line[1]);
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
