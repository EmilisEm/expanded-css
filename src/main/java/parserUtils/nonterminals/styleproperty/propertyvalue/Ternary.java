package parserUtils.nonterminals.styleproperty.propertyvalue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Ternary extends SelectorPropertyValue {
    private TernaryCondition condition;
    private List<SelectorPropertyValue> rightValue;
    private List<SelectorPropertyValue> leftValue;

    @Override
    public String getValue() {
        return "| %s : %s ? %s : %s |".formatted(
                condition.getType().getValue(),
                condition.getValue(),
                valueToString((rightValue)),
                valueToString(leftValue));
    }

    public static String valueToString(List<SelectorPropertyValue> values) {
        StringBuilder ac = new StringBuilder();
        values.forEach(it -> ac.append(it.getValue()));

        return ac.toString();
    }
}
