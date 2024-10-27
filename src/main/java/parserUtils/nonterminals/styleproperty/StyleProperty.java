package parserUtils.nonterminals.styleproperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import parserUtils.nonterminals.styleproperty.propertyvalue.value.PropertyValue;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StyleProperty {
    private String propertyName;
    private List<PropertyValue> propertyValues;
}
