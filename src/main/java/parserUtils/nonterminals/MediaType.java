package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaType {
    SCREEN("SCREEN"),
    AURAL("AURAL");

    private final String value;
}
