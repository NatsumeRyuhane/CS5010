package priority;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * A min-max priority queue implementation.
 * This class uses a TreeMap to map priorities to a LinkedList of items,
 * to simulate the FIFO behavior of a queue.
 *
 * @param <T> The type of the items in the queue.
 */
public class MinMaxPriorityQueueImpl<T> implements MinMaxPriorityQueue<T> {
    // The TreeMap is used to map priorities to a LinkedList of items.
    // The reason for using a TreeMap is to keep the priorities sorted.
    private final TreeMap<Integer, LinkedList<T>> priorityToItemMap;

    public MinMaxPriorityQueueImpl() {
        this.priorityToItemMap = new TreeMap<>();
    }

    @Override
    public void add(T item, int priority) {
        // If the priority does not exist in the map, create a new LinkedList.
        this.priorityToItemMap.computeIfAbsent(priority, (k -> new LinkedList<>()));

        // Add the item to the LinkedList.
        this.priorityToItemMap.get(priority).add(item);
    }

    private T remove(TreeMap<Integer, LinkedList<T>> map, boolean isMin) {
        // Get the entry of the highest or lowest priority in the map,
        // depending on the value of isMin flag.
        Map.Entry<Integer, LinkedList<T>> entry = isMin ? map.firstEntry() : map.lastEntry();

        // If there are no items in the map, return null.
        if (entry == null) {
            return null;
        }

        // Get the LinkedList of items.
        LinkedList<T> items = entry.getValue();

        // Remove the first item from the LinkedList.
        T item = items.poll();
        // If the LinkedList is empty, remove the entry from the map.
        if (items.isEmpty()) {
            map.remove(entry.getKey());
        }
        return item;
    }

    @Override
    public T minPriorityItem() {
        return remove(this.priorityToItemMap, true);
    }

    @Override
    public T maxPriorityItem() {
        return remove(this.priorityToItemMap, false);
    }
}
