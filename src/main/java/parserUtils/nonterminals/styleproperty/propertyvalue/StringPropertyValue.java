package parserUtils.nonterminals.styleproperty.propertyvalue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StringPropertyValue extends SelectorPropertyValue {
    private String value;
}