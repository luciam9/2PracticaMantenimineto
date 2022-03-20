package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleEndedQueueTest {

    private DoubleLinkedListQueue queue;
    private DoubleLinkedListQueue emptyQueue;

    @BeforeEach
    public void setNode(){

        /*DequeNode first = new DequeNode(1, null, null);
        DequeNode node = new DequeNode(2, null, first);
        first.setNext(node);
        DequeNode last =  new DequeNode(3, null, node);
        node.setNext(last);*/

        queue = new DoubleLinkedListQueue();
        queue.append(new DequeNode(1, null, null));
        queue.append(new DequeNode(2, null, null));

        emptyQueue = new DoubleLinkedListQueue();
    }

    @AfterEach
    public void destroy(){
        queue = null;
        emptyQueue = null;
    }

    @Test
    public void appendEmptyQueueWithValidNodeAddsNode(){
        DequeNode node = new DequeNode(1, null, null);
        emptyQueue.append(node);
        assertEquals(node, emptyQueue.peekFirst());
        assertEquals(node, emptyQueue.peekLast());
    }

    @Test
    public void appendNotEmptyQueueWithValidNodeAddsNode(){
        DequeNode node = new DequeNode(3, null, null);
        queue.append(node);
        assertEquals(node.getItem(), queue.peekLast().getItem());
    }

    @Test
    public void appendNotEmptyQueueWithNotValidNodeThrowsException(){
        DequeNode node = new DequeNode(3, new DequeNode(4, null, null), null);
        assertThrows(RuntimeException.class, () -> queue.append(node));
    }

    @Test
    public void appendLeftEmptyQueueWithValidNodeAddsNode(){
        DequeNode node = new DequeNode(1, null, null);
        emptyQueue.appendLeft(node);
        assertEquals(node, emptyQueue.peekFirst());
        assertEquals(node, emptyQueue.peekLast());
    }

    @Test
    public void appendLeftNotEmptyQueueWithValidNodeAddsNode(){
        DequeNode node = new DequeNode(3, null, null);
        queue.appendLeft(node);
        assertEquals(node.getItem(), queue.peekFirst().getItem());
    }

    @Test
    public void appendLeftNotEmptyQueueWithNotValidNodeThrowsException(){
        DequeNode node = new DequeNode(3, new DequeNode(4, null, null), null);
        assertThrows(RuntimeException.class, () -> queue.appendLeft(node));
    }

    @Test
    public void deleteFirstNotEmptyQueueDeletesFirstNode(){
        DequeNode first = queue.peekFirst().getNext();
        queue.deleteFirst();

        assertEquals(first.getItem(), queue.peekFirst().getItem());
    }

    @Test
    public void deleteFirstEmptyQueueThrowsException(){
        assertThrows(RuntimeException.class, () -> emptyQueue.deleteFirst());
    }

    @Test
    public void deleteLastNotEmptyQueueDeletesLastNode(){
        DequeNode last = queue.peekLast().getPrevious();
        queue.deleteLast();

        assertEquals(last.getItem(), queue.peekLast().getItem());
    }

    @Test
    public void deleteLastEmptyQueueThrowsException(){
        assertThrows(RuntimeException.class, () -> emptyQueue.deleteLast());
    }

    @Test
    public void sizeEmptyQueueIsZero(){

        assertEquals(0, emptyQueue.size());
    }

    @Test
    public void sizeNotEmptyQueueIsNumberOfElements(){

        assertEquals(2, queue.size());
    }

    @Test
    public void findItemInAQueueWhenItemIsContainedReturnsTheNode(){
        assertEquals(2,queue.find(2).getItem());
    }

    @Test
    public void findItemInAQueueWhenItemIsNotContainedReturnsNull(){
        assertNull(queue.find(3));
    }

    @Test
    public void findItemInAEmptyQueueReturnsNull(){
        assertNull(emptyQueue.find(3));
    }

}