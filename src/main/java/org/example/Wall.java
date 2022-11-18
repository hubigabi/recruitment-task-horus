package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    /**
     * I assumed that blocks contains only {@link Block}s that are not {@link CompositeBlock}s
     */
    private List<Block> blocks;

    public Wall(Block block) {
        blocks = new ArrayList<>();

        // Putting logic inside constructor is probably not good practise,
        // so this method should be moved to a new class and static method e.g. ConvertBlocks.getOnlyBlocksFromCompositeBlocks(Block block),
        // and then use it like this: new Wall(ConvertBlocks.getOnlyBlocksFromCompositeBlocks(Block block))
        // but in task description was pointed that implementation should be only added in Wall class
        setBlocks(block);
    }

    private void setBlocks(Block block) {
        if (block instanceof CompositeBlock compositeBlock) {
            // If my assumption was not correct then next line should be uncommented:
            // "I assumed that {@link Wall#blocks} contains only {@link Block}s that are not {@link CompositeBlock}s"
            // this.blocks.add(compositeBlock);
            for (Block childrenBlock : compositeBlock.getBlocks()) {
                setBlocks(childrenBlock);
            }
        } else {
            this.blocks.add(block);
        }
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return blocks.size();
    }

}
