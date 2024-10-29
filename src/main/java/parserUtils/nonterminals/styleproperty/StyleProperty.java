package parserUtils.nonterminals.styleproperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parserUtils.nonterminals.styleproperty.propertyvalue.value.PropertyValue;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StyleProperty {
    private String propertyName;
    private List<PropertyValue> propertyValues;
}
