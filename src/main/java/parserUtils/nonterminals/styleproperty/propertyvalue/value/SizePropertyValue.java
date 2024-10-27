package parserUtils.nonterminals.styleproperty.propertyvalue.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SizePropertyValue extends PropertyValue {
    private Integer value;
    private MeasurementType measurementType;
    private String rawValue;
}
