import java.util.Iterator;
import java.util.List;

public class CustomIteratorUsers<E> implements Iterator<E> {
    private int currentPosition;
    private final List<E> list;

    public CustomIteratorUsers(List<E> list) {
        this.currentPosition = 0;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < list.size();
    }

    @Override
    public E next() {
        E element = list.get(currentPosition);
        currentPosition++;
        return element;
    }

    public Streams next(Integer id) {
        while(hasNext()) {
            E s = next();
            if (s != null && ((Streams) s).getId().equals(id)) {
                return (Streams) s;
            }
        }
        return null;

    }
    public void resetPozition() {
        currentPosition = 0;
    }

}