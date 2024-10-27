package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SelectorRelationshipType {
    DESCENDENT("DESCENDENT"),
    DIR_DESCENDENT("DIR_DESCENDENT");

    private final String type;
}