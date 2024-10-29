import cssParser.CssParser;
import cssParser.sym;
import java_cup.runtime.Symbol;
import scanner.Lexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String in;
        FileReader input = null;

        if (args.length != 3) {
            System.err.println("Not enough argument. Example execution `java Main input.txt out_good.txt out_bad.txt`");
            System.exit(1);
        }

        in = args[0];
        try {
            System.setOut(new PrintStream(args[1]));
            System.setErr(new PrintStream(args[2]));
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open specified files");
            System.exit(1);
        }

        try {
            input = new FileReader(in);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        var lexer = new Lexer(input);
        var parser = new CssParser(lexer);
        Symbol val = null;
        try {
            val = parser.parse();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Something went wrong :( ");
            System.exit(1);
        }

        System.err.println(val.value);

    }

    // debug method
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
