package scanner;
import cssParser.sym;
import java_cup.runtime.Symbol;

public abstract class ScanUtils {
    public static Symbol symbol(int type, Object value) {
        return new Symbol(type, value);
    }

    public static Symbol symbol(int type) {
        return new Symbol(type);
    }

    public static String parseUrl(String url) {
        if (url.startsWith("url(") && url.endsWith(")") && url.length() > 5) {
            return url.substring(4, url.length() - 1);
        }

        throw new IllegalArgumentException("url is of invalid content");
    }

    public static String parseMedia(String media) {
        return media.substring(7);
    }

    public static Symbol getSelector(String selector) {
        int symbolVal;
        if (selector.startsWith(".")) {
            symbolVal = sym.CLASS;
        }
        else if (selector.startsWith("#")) {
            symbolVal = sym.ID;
        }
        else {
            symbolVal = sym.TAG;
            return symbol(symbolVal, selector.toUpperCase());
        }

        return symbol(symbolVal, selector);
    }
}
