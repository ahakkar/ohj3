/*
 * COMP.CS.140 Ohjelmointi 3
 * H7 JSON
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class for representing a JSON object.
 */
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


    /**
     * Constructs an initially empty JSON object node.
     */
    public ObjectNode() {
        this.map = new HashMap<String, Node>();
    }


    /**
     * Returns a String iterator that iterates the names of the name-node pairs stored in this JSON object in natural String order.
     * @return a String iterator that iterates the names of the stored name-node pairs in natural String order.
     */
    @Override
    public Iterator<String> iterator() {
        return new ObjectNodeIterator(map);
    }

    /**
     * Returns the JSON node stored under the given name.
     * @param key - the name of the name-node pair whose node should be returned.
     * @return the JSON node corresponding to name, or null if such node does not exist.
     */
    public Node get(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    /**
     * Stores a name - JSON node pair into this JSON object. If a name-node pair with the same name already exists, the previously existing node will be replaced.
     * @param key - the name of the name-node pair.
     * @param value - the JSON node of the name-node pair.
     */
    public void set(String key, Node value) {
        map.put(key, value);

    }


    /**
     * Returns the number of JSON nodes stored under this JSON object.
     * @return the number of JSON nodes under this JSON object.
     */
    public int size() {
        return map.size();
    }
}

