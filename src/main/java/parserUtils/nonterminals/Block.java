package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import parserUtils.nonterminals.styleproperty.StyleProperty;

import java.nio.channels.Selector;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Block {
    private Selector selector;
    private List<StyleProperty> stylePropertyList;
}
