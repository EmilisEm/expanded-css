package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum MediaType {
    SCREEN("SCREEN"),
    AURAL("AURAL");

    private final String value;
}
