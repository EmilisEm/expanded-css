package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MediaBlock {
    private MediaType type;
    private List<Block> blocks;
}
