package parserUtils.nonterminals.styleproperty.propertyvalue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Ternary extends PropertyValue {
    private TernaryCondition condition;
    private List<PropertyValue> rightValue;
    private List<PropertyValue> leftValue;

    @Override
    public String getValue() {
        return "| %s : %s ? %s : %s |".formatted(
                condition.getType().getValue(),
                condition.getValue(),
                valueToString((rightValue)),
                valueToString(leftValue));
    }

    public static String valueToString(List<PropertyValue> values) {
        StringBuilder ac = new StringBuilder();
        values.forEach(it -> ac.append(it.getValue()));

        return ac.toString();
    }
}
