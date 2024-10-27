package parserUtils.nonterminals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StyleSheet {
    List<Block> blocks;
    List<MediaBlock> mediaBlocks;
}
