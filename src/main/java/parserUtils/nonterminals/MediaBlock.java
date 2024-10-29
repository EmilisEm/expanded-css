package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MediaBlock implements Block {
    private MediaType type;
    private List<SelectorBlock> selectorBlocks;
}
