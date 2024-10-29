import cssParser.CssParser;
import cssParser.sym;
import java_cup.runtime.Symbol;
import scanner.Lexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
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
            System.exit(1);
        }

        System.out.println(prettyPrintStyleSheet(val.value.toString()));

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

    public static String prettyPrintStyleSheet(String styleSheetStr) {
        StringBuilder prettyOutput = new StringBuilder();
        int indentLevel = 0;
        boolean inBracketedBlock = false;

        for (int i = 0; i < styleSheetStr.length(); i++) {
            char c = styleSheetStr.charAt(i);

            if (c == '[' || c == '(') {
                if (inBracketedBlock) {
                    prettyOutput.append('\n').append(getIndentation(indentLevel));
                }
                prettyOutput.append(c).append('\n');
                indentLevel++;
                prettyOutput.append(getIndentation(indentLevel));
                inBracketedBlock = true;
            } else if (c == ']' || c == ')') {
                prettyOutput.append('\n');
                indentLevel--;
                prettyOutput.append(getIndentation(indentLevel)).append(c);
                inBracketedBlock = false;
            }
            else if (c == ',') {
                prettyOutput.append(c).append('\n').append(getIndentation(indentLevel));
                inBracketedBlock = true;
            }
            else if (styleSheetStr.startsWith("selector=", i) || styleSheetStr.startsWith("propertyName=", i)) {
                prettyOutput.append('\n').append(getIndentation(indentLevel)).append(c);
            } else {
                prettyOutput.append(c);
            }
        }
        return prettyOutput.toString();
    }

    private static String getIndentation(int level) {
        return "  ".repeat(Math.max(0, level));  // 4 spaces per indentation level
    }
}
