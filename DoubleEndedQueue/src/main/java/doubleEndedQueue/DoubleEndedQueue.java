package doubleEndedQueue;

import java.util.Comparator;
import java.util.Deque;

public interface DoubleEndedQueue<T> {

    // Basic operations
    void append(DequeNode<T> node) ;
    void appendLeft(DequeNode<T> node) ;
    void deleteFirst() ;
    void deleteLast() ;
    DequeNode<T> peekFirst() ;
    DequeNode<T> peekLast() ;
    int size() ;

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica.)
    DequeNode<T> find (T item) ;
    void delete(DequeNode<T> node);
    DequeNode<T> getAt(int position);
    void sort(Comparator<?> comparator);
}
