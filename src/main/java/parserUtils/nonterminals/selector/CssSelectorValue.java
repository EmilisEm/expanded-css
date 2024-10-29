package parserUtils.nonterminals.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CssSelectorValue {
    private String content;
    private CssSelectorType type;
}
