package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import parserUtils.nonterminals.Inheritance;

@Getter
@Setter
@AllArgsConstructor
public class CssSelector {
    private SelectorGroup selectorGroup;
    private Inheritance inheritance;
}
