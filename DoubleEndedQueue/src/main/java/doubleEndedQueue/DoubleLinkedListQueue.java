package doubleEndedQueue;

import java.util.Comparator;

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

    @Override
    public DequeNode getAt(int position){

        if(position>=this.size()){
            throw new RuntimeException("ERROR: Position not valid");
        }else{
            int cont = 0;
            DequeNode actual = this.first;

            while(cont!=position){
                actual = actual.getNext();
                cont++;
            }

            return actual;
        }
    }



    private boolean isEmpty(){
        return first==null && last==null;
    }

    @Override
    public DequeNode find(Object item) {
        if(isEmpty()){
            return null;
        } else {
            DequeNode actual = this.first;
            while (actual!=null){

                if(actual.getItem().equals(item)){
                    return actual;
                }else{
                    actual=actual.getNext();
                }
            }


        }
        return null;

    }

    @Override
    public void delete(DequeNode node) {
        if(find(node.getItem())!=null)
        {
            DequeNode nodeFound = find(node.getItem());
            if(nodeFound.isLastNode() && nodeFound.isFirstNode()){
                this.first = null;
                this.last = null;
            }else if(nodeFound.isFirstNode())
            {
                first = nodeFound.getNext();
                nodeFound.getNext().setPrevious(null);
            }
            else if(nodeFound.isLastNode())
            {
                last = nodeFound.getPrevious();
                nodeFound.getPrevious().setNext(null);
            }
            else
            {
                nodeFound.getNext().setPrevious(nodeFound.getPrevious());
                nodeFound.getPrevious().setNext(nodeFound.getNext());
            }
        }
        else
        {
            throw new RuntimeException("ERROR: Node doesn't exist");
        }
    }

    private Object getMinimum(Comparator comparator){

        DequeNode min = this.first;

        for(int i = 1; i<size(); i++){
            DequeNode actual = getAt(i);
            if(comparator.compare(min.getItem(),actual.getItem())>0){
                min = actual;
            }
        }

        return min.getItem();

    }

    @Override
    public void sort(Comparator comparator) {
         DoubleLinkedListQueue auxiliar = new DoubleLinkedListQueue();
         while(!this.isEmpty()){
             Object minimum = getMinimum(comparator);
             this.delete(new DequeNode(minimum,null,null));
             auxiliar.append(new DequeNode<>(minimum,null,null));
         }

         while (!auxiliar.isEmpty()){
             this.append(new DequeNode(auxiliar.getAt(0).getItem(),null,null));
             auxiliar.delete(this.last);
         }

    }


}
