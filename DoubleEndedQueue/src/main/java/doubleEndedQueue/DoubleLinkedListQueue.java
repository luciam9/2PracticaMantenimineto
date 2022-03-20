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

        if(!(node.isLastNode() && node.isFirstNode())){
            throw new RuntimeException("ERROR: Given node is not valid");
        }
        if(first==null && last==null){
            first=node;
            last = node;
        }else{
            DequeNode nuevo = new DequeNode(node.getItem(), null, last);
            this.last.setNext(nuevo);
            this.last = nuevo;
        }
    }

    @Override
    public void appendLeft(DequeNode node) {

        if(!(node.isLastNode() && node.isFirstNode())){
            throw new RuntimeException("ERROR: Given node is not valid");
        }
        if(first==null && last==null){
            first=node;
            last = node;
        }else {
            DequeNode nuevo = new DequeNode(node.getItem(), first, null);
            this.first = nuevo;
        }
    }

    @Override
    public void deleteFirst() {

        if(first==null && last==null){
            throw new RuntimeException("ERROR: Empty queue");
        }else {
            first = first.getNext();
        }
    }

    @Override
    public void deleteLast() {

        if(first==null && last==null){
            throw new RuntimeException("ERROR: Empty queue");
        }else {
            DequeNode last = this.last;
            DequeNode previousToLast = this.last.getPrevious();

            this.last = previousToLast;
            this.last.setNext(null);
        }
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

        if(first==null && last==null){
            return 0;
        }else {
        int size = 1;
        DequeNode actual = this.first;

        while (!actual.isLastNode()){
            size++;
            actual = actual.getNext();
        }

        return size;
        }
    }
}
