/*
 * COMP.CS.140 Ohjelmointi 3
 * H7 JSON
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectNode extends Node implements Iterable<String> {

    /* sorts the keys to lexicographical alphabetical order.. */
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

