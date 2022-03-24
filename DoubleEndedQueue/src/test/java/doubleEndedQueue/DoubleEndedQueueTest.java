package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class DoubleEndedQueueTest {

    private DoubleLinkedListQueue queue;
    private DoubleLinkedListQueue emptyQueue;
    private DoubleLinkedListQueue unsortedIntegerQueue;



    @BeforeEach
    public void setNode(){
        queue = new DoubleLinkedListQueue();
        queue.append(new DequeNode(1, null, null));
        queue.append(new DequeNode(2, null, null));
        queue.append(new DequeNode(3, null, null));

        emptyQueue = new DoubleLinkedListQueue();


        unsortedIntegerQueue = new DoubleLinkedListQueue();
        unsortedIntegerQueue.append(new DequeNode<>(4,null,null));
        unsortedIntegerQueue.append(new DequeNode<>(5,null,null));
        unsortedIntegerQueue.append(new DequeNode<>(2,null,null));
        unsortedIntegerQueue.append(new DequeNode<>(10,null,null));



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

        assertEquals(3, queue.size());
    }

    @Test
    public void findItemInAQueueWhenItemIsContainedReturnsTheNode(){
        assertEquals(2,queue.find(2).getItem());
    }

    @Test
    public void findItemInAQueueWhenItemIsNotContainedReturnsNull(){
        assertNull(queue.find(4));
    }

    @Test
    public void findItemInAEmptyQueueReturnsNull(){
        assertNull(emptyQueue.find(3));
    }

    @Test
    public void deletingFirstNodeMakesNextNodeFirst()
    {
        DequeNode first = queue.peekFirst();
        DequeNode nextToFirst = queue.peekFirst().getNext();

        queue.delete(first);

        assertEquals(queue.peekFirst().getItem(), nextToFirst.getItem());
    }

    @Test
    public void deletingLastNodeMakesPreviousNodeLast()
    {
        DequeNode last = queue.peekLast();
        DequeNode previousToLast = queue.peekLast().getPrevious();

        queue.delete(last);

        assertEquals(queue.peekLast().getItem(), previousToLast.getItem());
    }

    @Test
    public void deletingMiddleNodeJoinsPreviousAndNextNodes()
    {
        DequeNode actual = queue.find(2);
        DequeNode previous = actual.getPrevious();
        DequeNode next = actual.getNext();

        queue.delete(actual);

        assertEquals(previous.getNext().getItem(), next.getItem());
    }

    @Test
    public void getAtPositionInNotEmptyQueueReturnsRightNode(){

        DequeNode node = queue.peekFirst();

        assertEquals(queue.peekFirst().getItem(), queue.getAt(0).getItem());
    }

    @Test
    public void getAtPosition2InNotEmptyQueueReturnsRightNode(){

        DequeNode node = queue.find(2);

        assertEquals(node.getItem(), queue.getAt(1).getItem());
    }

    @Test
    public void getAtPositionInEmptyQueueThrowsException(){
        assertThrows(RuntimeException.class, () -> emptyQueue.getAt(0));
    }
    public void deletingMiddleNodeJoinsNextAndPreviousNodes()
    {
        DequeNode actual = queue.find(2);
        DequeNode previous = actual.getPrevious();
        DequeNode next = actual.getNext();

        queue.delete(actual);

        assertEquals(next.getPrevious().getItem(), previous.getItem());
    }

    @Test
    public void deletingNonExistingNodeThrowsAnException()
    {
        assertThrows(RuntimeException.class, () -> queue.delete(new DequeNode(10, null, null)));
    }

    @Test
    public void deleteWhenThereIsOneNodeInQueue(){
        queue.delete(new DequeNode<>(2,null,null));
        queue.delete(new DequeNode<>(3,null,null));
        queue.delete(new DequeNode(1,null,null));
        assertEquals(0, queue.size());

    }

    @Test
    public void sortFromSmallestToLargestFromASortedQueue(){
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        DoubleLinkedListQueue queueUnsorted = queue;
        queue.sort(comp);

        assertEquals(queueUnsorted, queue);


    }

    @Test
    public void sortFromSmallestToLargestFromAUnsortedQueue(){
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        DoubleLinkedListQueue queueUnsorted = unsortedIntegerQueue;
        unsortedIntegerQueue.sort(comp);

        assertEquals(queueUnsorted, unsortedIntegerQueue);


    }

}