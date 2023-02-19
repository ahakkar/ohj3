import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectNodeIterator implements Iterator<String> {
    private Iterator<String> iterator;



    public ObjectNodeIterator(Map<String, Node> map) {
        iterator = sortSet(map.keySet()).iterator();
    }

    public static Set<String> sortSet(Set<String> set) {
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        return new LinkedHashSet<>(list);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public String next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}