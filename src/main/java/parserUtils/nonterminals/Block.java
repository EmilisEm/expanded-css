package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import parserUtils.nonterminals.selector.CssSelector;
import parserUtils.nonterminals.styleproperty.StyleProperty;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Block {
    private CssSelector selector;
    private List<StyleProperty> stylePropertyList;
}
