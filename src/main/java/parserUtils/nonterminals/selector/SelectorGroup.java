package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SelectorGroup {
    private List<CssSelectorValue> selectors;
    private List<SelectorRelationshipType> relationshipTypes;
}
