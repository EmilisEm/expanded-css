package parserUtils.nonterminals.styleproperty.propertyvalue.ternary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TernaryCondition {
    private ConditionType type;
    private String value;
}
