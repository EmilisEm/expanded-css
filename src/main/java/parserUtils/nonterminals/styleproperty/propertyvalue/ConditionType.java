package parserUtils.nonterminals.styleproperty.propertyvalue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConditionType {
    VERTICAL("VERTICAL"),
    HORIZONTAL("HORIZONTAL"),
    WIDTH_MIN("WIDTH_MIN"),
    WIDTH_MAX("WIDTH_MAX"),
    HEIGHT_MIN("HEIGHT_MIN"),
    HEIGHT_MAX("HEIGHT_MAX");

    private final String value;
}
