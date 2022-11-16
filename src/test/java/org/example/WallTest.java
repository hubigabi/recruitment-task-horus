package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    final String[] colors = {"red", "green", "blue"};
    final String[] materials = {"wood", "glass", "metal"};

    @Test
    void findBlockByColorTest() {
        List<Block> blocks = getBlocks();
        Wall wall = new Wall(blocks);
        Optional<Block> redBlock = wall.findBlockByColor("red");

        assertTrue(redBlock.isPresent());
        assertEquals("red", redBlock.get().getColor());
    }

    @Test
    void findEmptyBlockByColorTest() {
        List<Block> blocks = getBlocks();
        Wall wall = new Wall(blocks);
        Optional<Block> blackBlock = wall.findBlockByColor("black");

        assertTrue(blackBlock.isEmpty());
    }

    @Test
    void findBlocksByMaterialTest() {
        List<Block> blocks = getBlocks();
        Wall wall = new Wall(blocks);
        List<Block> woodenBlocks = wall.findBlocksByMaterial("wood");

        assertEquals(3, woodenBlocks.size());
        for (Block block : woodenBlocks) {
            assertEquals("wood", block.getMaterial());
        }
    }

    @Test
    void countTest() {
        List<Block> blocks = getBlocks();
        Wall wall = new Wall(blocks);

        assertEquals(blocks.size(), wall.count());
    }

    private List<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (String color : colors) {
            for (String material : materials) {
                Block block = new Block() {
                    @Override
                    public String getColor() {
                        return color;
                    }

                    @Override
                    public String getMaterial() {
                        return material;
                    }
                };
                blocks.add(block);
            }
        }
        return blocks;
    }

}