package parserUtils.nonterminals.styleproperty.propertyvalue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SizePropertyValue extends SelectorPropertyValue {
    private Integer value;
    private MeasurementType measurementType;
    private String rawValue;
}
