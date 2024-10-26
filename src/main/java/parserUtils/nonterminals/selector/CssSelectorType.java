package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CssSelectorType {
    CLASS("CLASS"), ID("ID"), TAG("TAG");

    private final String type;
}
