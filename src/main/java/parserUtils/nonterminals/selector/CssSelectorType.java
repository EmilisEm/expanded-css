package parserUtils.nonterminals.selector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum CssSelectorType {
    CLASS("CLASS"),
    ID("ID"),
    TAG("TAG");

    private final String type;
}
