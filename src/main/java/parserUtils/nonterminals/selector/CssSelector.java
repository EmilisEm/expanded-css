package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parserUtils.nonterminals.Inheritance;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CssSelector {
    private SelectorGroup selectorGroup;
    private Inheritance inheritance;
    private boolean important;
}
