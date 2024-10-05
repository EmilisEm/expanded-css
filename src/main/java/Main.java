import cssParser.sym;
import java_cup.runtime.Symbol;
import scanner.Lexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        FileReader input = null;
        try {
            input = new FileReader("src/test/java/testdata/basic/input.txt");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        var lexer = new Lexer(input);
        Symbol symbol = null;
        System.out.print("# Parsed lexemes. Errors in different file\r\n\r\n");
        System.out.print("| Lexeme name     | Lexeme value    |\r\n");
        System.out.print("|_________________|_________________|\r\n");
        do {
            try {
                symbol = lexer.next_token();
                // debug print
                System.out.println(getSymbolDebug(symbol));

                // actual
                // System.out.printf("| %-15s | %-15s |\r\n", getFieldName(symbol.sym), Objects.requireNonNullElse(symbol.value, ""));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (symbol == null || symbol.sym != sym.EOF);
    }

    private static String getSymbolDebug(Symbol symbol) {
        return "%s %s".formatted(getFieldName(symbol.sym), Objects.requireNonNullElse(symbol.value, ""));
    }

    private static String getFieldName(int fieldValue) {
        Field[] fields = sym.class.getFields();

        for (Field field : fields) {
            try {
                if (field.getInt(null) == fieldValue) {
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }

        throw new IllegalArgumentException("Field with value %d does not exist in class sym".formatted(fieldValue));
    }
}
