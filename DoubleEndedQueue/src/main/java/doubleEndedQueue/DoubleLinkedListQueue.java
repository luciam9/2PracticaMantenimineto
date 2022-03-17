package doubleEndedQueue;

public class DoubleLinkedListQueue implements DoubleEndedQueue{

    private DequeNode first;
    private DequeNode last;

    public DoubleLinkedListQueue(){
        first = null;
        last = null;
    }

    @Override
    public void append(DequeNode node) {

        if(first==null && last==null){

            if(!(node.isLastNode() && node.isFirstNode())){
                throw new RuntimeException("ERROR: Given node is not valid");
            }
            first=node;
            last = node;
        }else{
            DequeNode nuevo = new DequeNode(node.getItem(), null, last);
            this.last = nuevo;
        }
    }

    @Override
    public void appendLeft(DequeNode node) {
        DequeNode nuevo = new DequeNode(node.getItem(), first, null);
        this.first = nuevo;
    }

    @Override
    public void deleteFirst() {
        first = first.getNext();
    }

    @Override
    public void deleteLast() {
        DequeNode last = this.last;
        DequeNode previousToLast = this.last.getPrevious();

        this.last = previousToLast;
        this.last.setNext(null);
    }

    @Override
    public DequeNode peekFirst() {
        return this.first;
    }

    @Override
    public DequeNode peekLast() {
        return this.last;
    }

    @Override
    public int size() {
        int size = 0;
        DequeNode actual = this.first;

        while (!actual.isLastNode()){
            size++;
            actual = actual.getNext();
        }

        return size;
    }
}
