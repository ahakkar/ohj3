import java.util.ArrayList;
import java.util.Iterator;

public class ArrayNode extends Node implements Iterable<Node> {
    
    private ArrayList<Node> arr;

    public ArrayNode() {
        arr = new ArrayList<Node>();
    }

    public void add(Node node) {
        arr.add(node);
    }

    public int size() {
        return arr.size();
    }

    @Override
    public Iterator<Node> iterator() {
        return arr.iterator();  
    }
}
