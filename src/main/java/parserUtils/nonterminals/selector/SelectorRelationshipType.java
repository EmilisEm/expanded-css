package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum SelectorRelationshipType {
    DESCENDENT(" "),
    DIR_DESCENDENT(">");

    private final String type;
}
