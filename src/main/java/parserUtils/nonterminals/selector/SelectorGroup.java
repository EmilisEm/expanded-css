package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SelectorGroup {
    private List<CssSelectorValue> selectors;
    private List<SelectorRelationshipType> relationshipTypes;
}
