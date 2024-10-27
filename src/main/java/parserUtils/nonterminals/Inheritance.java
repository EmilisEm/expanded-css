package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import parserUtils.nonterminals.selector.CssSelectorValue;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Inheritance {
    private List<CssSelectorValue> inherited;
}
