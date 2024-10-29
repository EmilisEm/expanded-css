package parserUtils;

import lombok.experimental.UtilityClass;
import parserUtils.nonterminals.MediaType;
import parserUtils.nonterminals.styleproperty.propertyvalue.ternary.ConditionType;
import parserUtils.nonterminals.styleproperty.propertyvalue.ternary.TernaryCondition;
import parserUtils.nonterminals.styleproperty.propertyvalue.value.SizePropertyValue;

import java.util.ArrayList;

import static parserUtils.nonterminals.styleproperty.propertyvalue.ternary.ConditionType.*;
import static parserUtils.nonterminals.styleproperty.propertyvalue.value.MeasurementType.EM;
import static parserUtils.nonterminals.styleproperty.propertyvalue.value.MeasurementType.PX;

@UtilityClass
public class ParseUtils {
    public static SizePropertyValue parseSizeValue(String value) {
        if (value.length() < 3) {
            throw new IllegalArgumentException("Unexpected length of value");
        }
        var unitType = value.substring(value.length() - 2);
        var numberValue = Integer.parseInt(value.substring(0, value.length() - 2));

        if (unitType.equalsIgnoreCase("px")) {
            return new SizePropertyValue(numberValue, PX, value);
        }
        else if (unitType.equalsIgnoreCase("em")) {
            return new SizePropertyValue(numberValue, EM, value);
        }
        else {
            throw new IllegalArgumentException("Failed to parse size value");
        }
    }

    public static ConditionType getSizeCondition(String condition) {
        return switch (condition.toUpperCase()) {
            case "MIN-WIDTH" -> MIN_WIDTH;
            case "MAX-WIDTH" -> MAX_WIDTH;
            case "MIN-HEIGHT" -> MIN_HEIGHT;
            case "MAX-HEIGHT" -> MAX_HEIGHT;
            default -> throw new IllegalArgumentException("Unable to parse orientation condition");
        };
    }

    public static MediaType parseMediaType(String value) {
        return switch (value.toUpperCase()) {
            case "SCREEN" -> MediaType.SCREEN;
            case "AURAL" -> MediaType.AURAL;
            default -> throw new IllegalArgumentException("Failed to parse media type");
        };
    }

    public static ConditionType getOrientation(String value) {
        return switch (value.toUpperCase()) {
            case "VERTICAL" -> VERTICAL;
            case "HORIZONTAL" -> HORIZONTAL;
            default -> throw new IllegalArgumentException("Failed to parse orientation value");
        };
    }

    public static <T> ArrayList<T> listOf(T value) {
        ArrayList<T> list = new ArrayList<>();
        list.add(value);

        return list;
    }
}
