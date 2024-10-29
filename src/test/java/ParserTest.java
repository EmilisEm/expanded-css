import cssParser.CssParser;
import org.junit.Test;
import scanner.Lexer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParserTest {
    @Test
    public void parseSampleTest() {
        try {
            var answerReader = new BufferedReader(new FileReader("src/test/java/testdata/basic/output_parser.txt"));
            var input = new FileReader("src/test/java/testdata/basic/input.txt");
            var lexer = new Lexer(input);
            var parser = new CssParser(lexer);

            validateParserOutput(parser, answerReader.readLine());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void parseExpandedDataTest() {
        try {
            var answerReader = new BufferedReader(new FileReader("src/test/java/testdata/selector/output_parser.txt"));
            var input = new FileReader("src/test/java/testdata/selector/input.txt");
            var lexer = new Lexer(input);
            var parser = new CssParser(lexer);

            validateParserOutput(parser, answerReader.readLine());
        } catch (IOException e) {
            fail();
        }
    }

    private void validateParserOutput(CssParser parser, String expected) {
        try {
            assertEquals(expected, parser.parse().value.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
