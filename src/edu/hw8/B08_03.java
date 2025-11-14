package edu.hw8;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class B08_03 {

    public static class UndirectedGraph<V> {

        private final Map<V, Set<V>> adjacency = new HashMap<>();

        public void addVertex(V v) {
            adjacency.putIfAbsent(v, new HashSet<>());
        }

        public void removeVertex(V v) {
            Set<V> neighbours = adjacency.remove(v);
            if (neighbours == null) {
                return;
            }

            for (V neighbour : neighbours) {
                Set<V> set = adjacency.get(neighbour);
                if (set != null) {
                    set.remove(v);
                }
            }
        }

        public void addEdge(V v1, V v2) {
            if (v1 == null || v2 == null) {
                throw new IllegalArgumentException("Vertices must not be null");
            }

            addVertex(v1);
            addVertex(v2);

            adjacency.get(v1).add(v2);
            adjacency.get(v2).add(v1);
        }

        public void removeEdge(V v1, V v2) {
            Set<V> set1 = adjacency.get(v1);
            if (set1 != null) {
                set1.remove(v2);
            }

            Set<V> set2 = adjacency.get(v2);
            if (set2 != null) {
                set2.remove(v1);
            }
        }

        public Set<V> getVertices() {
            return Collections.unmodifiableSet(adjacency.keySet());
        }

        public Set<V> getNeighbours(V v) {
            Set<V> neighbours = adjacency.get(v);
            if (neighbours == null) {
                return Collections.emptySet();
            }
            return Collections.unmodifiableSet(neighbours);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<V, Set<V>> entry : adjacency.entrySet()) {
                sb.append(entry.getKey())
                  .append(" -> ")
                  .append(entry.getValue())
                  .append(System.lineSeparator());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        UndirectedGraph<String> g = new UndirectedGraph<>();
        g.addVertex("A");
        g.addVertex("B");
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "C");

        System.out.println("Graph:");
        System.out.println(g);

        g.removeEdge("A", "B");
        System.out.println("After removing edge A-B:");
        System.out.println(g);

        g.removeVertex("C");
        System.out.println("After removing vertex C:");
        System.out.println(g);
    }
}
