import java_cup.runtime.Symbol;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import scanner.Lexer;
import testUtils.AnswerReader;

import java.io.FileReader;
import java.io.IOException;

public class LexerTest {
    @Test
    public void basicCssTest() throws IOException {
        var answerReader = new AnswerReader(new FileReader("src/test/java/testdata/simple/output.txt"));
        var input = new FileReader("src/test/java/testdata/simple/input.txt");
        var lexer = new Lexer(input);

        validateLexerOutput(lexer, answerReader);
    }

    @Test
    public void selectorTest() throws IOException {
        var answerReader = new AnswerReader(new FileReader("src/test/java/testdata/selector/output.txt"));
        var input = new FileReader("src/test/java/testdata/selector/input.txt");
        var lexer = new Lexer(input);

        validateLexerOutput(lexer, answerReader);
    }

    @Test
    public void attributeTest() throws IOException {
        var answerReader = new AnswerReader(new FileReader("src/test/java/testdata/attribute/output.txt"));
        var input = new FileReader("src/test/java/testdata/attribute/input.txt");
        var lexer = new Lexer(input);

        validateLexerOutput(lexer, answerReader);
    }

    private void validateLexerOutput(Lexer lexer, AnswerReader answerReader) throws IOException {
        Symbol actual;
        Symbol expected;
        SoftAssertions soft = new SoftAssertions();

        while (!lexer.yyatEOF()) {
            actual = lexer.next_token();
            expected = answerReader.readNextSymbol();

            soft.assertThat(actual.sym).isEqualTo(expected.sym);
            soft.assertThat(actual.value).isEqualTo(expected.value);
        }

        soft.assertAll();
    }
}
