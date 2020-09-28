import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTests {

    // TESTING TREE CLASS

    @Test
    public void testInsertRoot() { // Also tests empty constructor for Tree class
        Tree nums = new Tree();
        nums.insert(5);
        assertEquals(1, nums.size(5));
    }

    @Test
    public void testFindingValueNotInTree() {
        Tree nums = new Tree();
        nums.insert(5);
        assertEquals(1, nums.size(5));
        assertEquals(0, nums.size(10));
        assertEquals(0, nums.size(1));
    }

    @Test
    public void testInsertingSameValueTwice() {
        Tree nums = new Tree();
        nums.insert(5);
        nums.insert(5);
    }

    @Test
    public void testFindingLeafToInsertAt() {
        Tree nums = new Tree();
        nums.insert(5);
        nums.insert(10);
        nums.insert(1);
        nums.insert(11);
    }

    @Test
    public void testAddBiggerNumberToNodeWithSpace() {
        Tree nums = new Tree();
        nums.insert(5);
        nums.insert(10);
        assertEquals(2, nums.size(5));
        assertEquals(2, nums.size(10));
        assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }

    @Test
    public void testAddSmallerNumberToNodeWithSpace() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        assertEquals(2, nums.size(5));
        assertEquals(2, nums.size(10));
        assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }

    @Test
    public void testMoveLeftUpToParent() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        nums.insert(1);
        nums.insert(8);
        nums.insert(11);
    }

    @Test
    public void testSplitLeft() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        nums.insert(1);
        assertEquals(3, nums.size(5));
        assertEquals(1, nums.size(10));
        assertEquals(1, nums.size(1));
        assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }

    @Test
    public void testSplitRight() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(5);
        nums.insert(10);
        assertEquals(3, nums.size(5));
        assertEquals(1, nums.size(10));
        assertEquals(1, nums.size(1));
        assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }

    @Test
    public void testSplitMiddle() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(10);
        nums.insert(5);
        assertEquals(3, nums.size(5));
        assertEquals(1, nums.size(10));
        assertEquals(1, nums.size(1));
        assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }


    // TESTING NODE CLASS

    @Test
    public void testGetKeys() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(10);
        nums.insert(5);
        nums.toString();
    }

    @Test
    public void testIsLeaf2Node() {
        Tree nums = new Tree();
        nums.insert(1);
        assertEquals(true, nums.getRoot().isLeaf());
        nums.insert(2);
        assertEquals(true, nums.getRoot().isLeaf());
    }

    @Test
    public void testGet3NodeSubtreeAddToLeft() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(10);
        nums.insert(5);
        nums.insert(2);
        nums.insert(3);
        nums.insert(0);
    }

    @Test
    public void testGet3NodeSubtreeAddToMiddle() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(10);
        nums.insert(5);
        nums.insert(2);
        nums.insert(3);
        nums.insert(4);
    }

    @Test
    public void testGet3NodeSubtreeAddToRight() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(10);
        nums.insert(5);
        nums.insert(2);
        nums.insert(3);
        nums.insert(8);
    }


}
