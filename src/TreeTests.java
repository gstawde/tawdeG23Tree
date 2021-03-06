import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTests {

    // TESTING TREE CLASS

    @Test
    public void testInsertRoot() { // Also tests empty constructor for Tree class
        Tree nums = new Tree();
        nums.insert(5);
        //System.out.println(nums.toString());
        assertEquals(1, nums.size(5));
    }

    @Test
    public void testCascade() {
        Tree nums = new Tree();
        nums.insert(50);
        nums.insert(25);
        nums.insert(75);
        nums.insert(15);
        nums.insert(35);
        assertEquals(1, nums.size(75));
        nums.insert(65);
        assertEquals(2, nums.size(75));
        nums.insert(100);
        assertEquals(3, nums.size(75));
        assertEquals(3, nums.size(25));
        nums.insert(70);
        assertEquals(4, nums.size(75));
        assertEquals(2, nums.size(65));
        nums.insert(68);
        assertEquals(5, nums.size(68));
        assertEquals(5, nums.size(75));
        assertEquals(1, nums.size(65));
        nums.insert(90);
        nums.insert(110);
        assertEquals(3, nums.size(68));
        assertEquals(11, nums.size(75));
    }

    @Test
    public void testEmptyTree() {
        Tree nums = new Tree();
        assertEquals(0, nums.size(5));
    }

    @Test
    public void testIncreasing() {
        Tree nums = new Tree();
        //System.out.println(nums.toString());
        assertEquals(0, nums.size(null));
        nums.insert(1);
        nums.insert(2);
        nums.insert(3);
        nums.insert(4);
        nums.insert(5);
        assertEquals(1, nums.size(5));
        assertEquals(5, nums.size(2));
    }

    @Test
    public void testDecreasing() {
        Tree nums = new Tree();
        //System.out.println(nums.toString());
        assertEquals(0, nums.size(null));
        nums.insert(5);
        nums.insert(4);
        nums.insert(3);
        nums.insert(2);
        nums.insert(1);
        assertEquals(1, nums.size(5));
        assertEquals(5, nums.size(2));
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
        assertEquals(1, nums.size(5));
        assertEquals(0, nums.size(10));
    }

    @Test
    public void testFindingLeafToInsertAt() {
        Tree nums = new Tree();
        nums.insert(5);
        nums.insert(10);
        nums.insert(1);
        nums.insert(11);
        assertEquals(2, nums.size(11));
        assertEquals(4, nums.size(5));
    }

    @Test
    public void testAddBiggerNumberToNodeWithSpace() {
        Tree nums = new Tree();
        nums.insert(5);
        nums.insert(10);
        assertEquals(2, nums.size(5));
        assertEquals(2, nums.size(10));
        //assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }

    @Test
    public void testAddSmallerNumberToNodeWithSpace() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        assertEquals(2, nums.size(5));
        assertEquals(2, nums.size(10));
        //assertEquals(5, nums.getRoot().getFirst(), 0.000001);
    }

    @Test
    public void testMoveRightUpToParent() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        nums.insert(1);
        nums.insert(8);
        nums.insert(11);
    }

    @Test
    public void testMoveLeftUpToParent() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        nums.insert(1);
        nums.insert(0);
        nums.insert(2);
    }

    @Test
    public void testMoveMiddleUpToParent() {
        Tree nums = new Tree();
        nums.insert(10);
        nums.insert(5);
        nums.insert(1);
        nums.insert(8);
        nums.insert(11);
        nums.insert(7);
        nums.insert(9);
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
    }


    // TESTING NODE CLASS

    @Test
    public void testGetKeys() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(10);
        nums.insert(5);
        // nums.toString();
    }

    @Test
    public void testIsLeaf2Node() {
        Tree nums = new Tree();
        nums.insert(1);
        nums.insert(2);
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

    @Test
    public void testCascadingSplitAndDuplicates() {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(17);
        t.insert(11);
        t.insert(27);
        t.insert(6);
        t.insert(3);
        t.insert(1);
        t.insert(9);
        t.insert(17);
        t.insert(1);
        t.insert(9);
        t.insert(17);
        t.insert(11);
        t.insert(27);

        assertEquals(7, t.size(9));
        assertEquals(3, t.size(3));
        assertEquals(3, t.size(17));

        assertEquals(0, t.size(12));
        assertEquals(1, t.size(11));
        assertEquals(0, t.size(14));
        assertEquals(0, t.size(19));
        assertEquals(1, t.size(27));
        assertEquals(0, t.size(21));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(2));

        assertEquals(1, t.size(6));
        assertEquals(0, t.size(7));
        assertEquals(0, t.size(8));

    }

}
