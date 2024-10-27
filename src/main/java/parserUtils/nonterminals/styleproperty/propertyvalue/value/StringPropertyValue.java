package parserUtils.nonterminals.styleproperty.propertyvalue.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StringPropertyValue extends PropertyValue {
    private String value;
}
