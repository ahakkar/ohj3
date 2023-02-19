import java.util.HashMap;
import java.util.Iterator;

public class ObjectNode extends Node implements Iterable<String> {

    private HashMap<String, Node> map;

    public ObjectNode() {
        this.map = new HashMap<String, Node>();
    }

    @Override
    public Iterator<String> iterator() {
        return new ObjectNodeIterator(map);
    }


    public Node get(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    public void set(String key, Node value) {
        map.put(key, value);

    }

    public int size() {
        return map.size();
    }
}

