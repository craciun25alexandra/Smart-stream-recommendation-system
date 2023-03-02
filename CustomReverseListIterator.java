import java.util.Iterator;
import java.util.List;

public class CustomReverseListIterator<E> implements Iterator<E> {
    private int currentPosition;
    private final List<E> list;

    public CustomReverseListIterator(List<E> list) {
        this.currentPosition = list.size() - 1;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return currentPosition >= 0;
    }

    @Override
    public E next() {
        E element = list.get(currentPosition);
        currentPosition--;
        return element;
    }

    public Streams next(Integer id, Long time) {
        while(hasNext()) {
            E s = next();
            if (s != null && ((Streams) s).getStreamerId().equals(id) && ((Streams) s).getDateAdded() < time) {
                return (Streams) s;
            }

        }
        return null;

    }

}