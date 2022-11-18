package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private final String[] colors = {"red", "green", "blue"};
    private final String[] materials = {"wood", "glass", "metal"};

    @Test
    void findBlockByColorTest() {
        Wall wall = new Wall(getCompositeBlock());
        Optional<Block> redBlock = wall.findBlockByColor("red");

        assertTrue(redBlock.isPresent());
        assertEquals("red", redBlock.get().getColor());
    }

    @Test
    void findEmptyBlockByColorTest() {
        Wall wall = new Wall(getCompositeBlock());
        Optional<Block> blackBlock = wall.findBlockByColor("black");

        assertTrue(blackBlock.isEmpty());
    }

    @Test
    void findBlocksByMaterialTest() {
        Wall wall = new Wall(getCompositeBlock());
        List<Block> woodenBlocks = wall.findBlocksByMaterial("wood");

        assertEquals(9, woodenBlocks.size());
        for (Block block : woodenBlocks) {
            assertEquals("wood", block.getMaterial());
        }
    }

    @Test
    void countTest() {
        Wall wall = new Wall(getCompositeBlock());
        assertEquals(27, wall.count());
    }

    public CompositeBlock getCompositeBlock() {
        final int BLOCKS_INSIDE_COMPOSITE_BLOCK = 3;
        return new CompositeBlock() {
            @Override
            public List<Block> getBlocks() {
                ArrayList<Block> blocks = new ArrayList<>();

                for (String color : colors) {
                    for (String material : materials) {
                        CompositeBlock compositeBlock = new CompositeBlock() {
                            @Override
                            public List<Block> getBlocks() {
                                ArrayList<Block> blocksInsideCompositeBlock = new ArrayList<>();
                                for (int i = 0; i < BLOCKS_INSIDE_COMPOSITE_BLOCK; i++) {
                                    blocksInsideCompositeBlock.add(getBlock(color, material));
                                }
                                return blocksInsideCompositeBlock;
                            }

                            @Override
                            public String getColor() {
                                return color;
                            }

                            @Override
                            public String getMaterial() {
                                return material;
                            }
                        };

                        blocks.add(compositeBlock);
                    }
                }
                return blocks;
            }

            @Override
            public String getColor() {
                return "color";
            }

            @Override
            public String getMaterial() {
                return "material";
            }
        };
    }

    private Block getBlock(String color, String material) {
        return new Block() {
            @Override
            public String getColor() {
                return color;
            }

            @Override
            public String getMaterial() {
                return material;
            }
        };
    }

}