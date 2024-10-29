package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parserUtils.nonterminals.selector.CssSelectorValue;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Inheritance {
    private List<CssSelectorValue> inherited;
}
