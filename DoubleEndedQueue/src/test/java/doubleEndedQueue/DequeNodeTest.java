package doubleEndedQueue;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DequeNodeTest {

    private DequeNode node;

    @BeforeEach
    public void setNode(){

        DequeNode first = new DequeNode(1, null, null);
        node = new DequeNode(2, null, first);
        first.setNext(node);
        DequeNode last =  new DequeNode(3, null, node);
        node.setNext(last);
    }

    @AfterEach
    public void destroy(){
        node = null;
    }

    @Test
    public void shouldGetTypeReturnNextItem(){
        int expectedValue = 3;
        assertEquals(expectedValue, node.getNext().getItem());
    }

    @Test
    public void shouldGetTypeReturnPreviousItem(){
        int expectedValue = 1;
        assertEquals(expectedValue, node.getPrevious().getItem());
    }

    @Test
    public void firstNodePreviousItemIsNull(){
        assertNull(node.getPrevious().getPrevious());
    }

    @Test
    public void lastNodeNextItemIsNull(){
        assertNull(node.getNext().getNext());
    }

    @Test
    public void lastNodeFunctionReturnsTrueIfNodeIsLast(){
       assertTrue(node.getNext().isLastNode());
    }

    @Test
    public void lastNodeFunctionReturnsFalseIfNodeIsNotLast(){
        assertFalse(node.isLastNode());
    }

    @Test
    public void firstNodeFunctionReturnsTrueIfNodeIsFirst(){
        assertTrue(node.getPrevious().isFirstNode());
    }

    @Test
    public void firstNodeFunctionReturnsFalseIfNodeIsNotFirst(){
        assertFalse(node.isFirstNode());
    }

    @Test
    public void nodeReturnsTrueIfNotTerminal(){
        assertTrue(node.isNotATerminalNode());
    }

    @Test
    public void nodeReturnsFalseIfTerminal(){
        assertFalse(node.getNext().isNotATerminalNode());
    }
}