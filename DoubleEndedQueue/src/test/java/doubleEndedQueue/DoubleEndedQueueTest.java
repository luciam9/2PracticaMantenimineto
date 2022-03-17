package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DoubleEndedQueueTest {

    private DoubleLinkedListQueue queue;

    @BeforeEach
    public void setNode(){

        DequeNode first = new DequeNode(1, null, null);
        DequeNode node = new DequeNode(2, null, first);
        first.setNext(node);
        DequeNode last =  new DequeNode(3, null, node);
        node.setNext(last);

        queue = new DoubleLinkedListQueue();
    }

    @AfterEach
    public void destroy(){
        
    }
}