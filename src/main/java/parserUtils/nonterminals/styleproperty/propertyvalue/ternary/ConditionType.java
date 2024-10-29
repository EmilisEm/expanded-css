package parserUtils.nonterminals.styleproperty.propertyvalue.ternary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConditionType {
    VERTICAL("VERTICAL"),
    HORIZONTAL("HORIZONTAL"),
    MIN_WIDTH("MIN-WIDTH"),
    MAX_WIDTH("MAX-WIDTH"),
    MIN_HEIGHT("MIN-HEIGHT"),
    MAX_HEIGHT("MAX-HEIGHT");

    private final String value;
}
