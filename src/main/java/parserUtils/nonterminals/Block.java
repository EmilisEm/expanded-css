package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parserUtils.nonterminals.selector.CssSelector;
import parserUtils.nonterminals.styleproperty.StyleProperty;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Block {
    private CssSelector selector;
    private List<StyleProperty> stylePropertyList;
}
