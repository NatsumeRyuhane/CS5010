package test;
import org.junit.Test;
import priority.MinMaxPriorityQueue;
import priority.MinMaxPriorityQueueImpl;


public class MinMaxPriorityQueueTest {
    @Test
    public void testMinPriority() {
        MinMaxPriorityQueue<TestData> minMaxPQ = new MinMaxPriorityQueueImpl<>();
        TestData[] data = new TestData[]{
                new TestData(1, 1),
                new TestData(2, 1),
                new TestData(3, 1),
                new TestData(4, 1),
                new TestData(5, 1),
                new TestData(6, 1),
                new TestData(7, 1),
                new TestData(8, 1),
                new TestData(9, 1),
                new TestData(10, 1),
        };

        for (TestData d : data) {
            minMaxPQ.add(d, d.priority);
        }

        for (int i = 1; i <= 10; i++) {
            TestData d = minMaxPQ.minPriorityItem();
            assert (d.priority == i && d.data == 1);
        }
    }

    @Test
    public void testMaxPriority() {
        MinMaxPriorityQueue<TestData> minMaxPQ = new MinMaxPriorityQueueImpl<>();
        TestData[] data = new TestData[]{
                new TestData(1, 1),
                new TestData(2, 1),
                new TestData(3, 1),
                new TestData(4, 1),
                new TestData(5, 1),
                new TestData(6, 1),
                new TestData(7, 1),
                new TestData(8, 1),
                new TestData(9, 1),
                new TestData(10, 1),
        };

        for (TestData d : data) {
            minMaxPQ.add(d, d.priority);
        }

        for (int i = 10; i >= 1; i--) {
            TestData d = minMaxPQ.maxPriorityItem();
            assert (d.priority == i && d.data == 1);
        }
    }

    @Test
    public void testSamePriorityDataOrder() {
        MinMaxPriorityQueue<TestData> minMaxPQ = new MinMaxPriorityQueueImpl<>();
        TestData[] data = new TestData[]{
                new TestData(1, 1),
                new TestData(1, 2),
                new TestData(1, 3),
                new TestData(1, 4),
                new TestData(1, 5),
                new TestData(1, 6),
                new TestData(1, 7),
                new TestData(1, 8),
                new TestData(1, 9),
                new TestData(1, 10),
        };

        for (TestData d : data) {
            minMaxPQ.add(d, d.priority);
        }

        for (int i = 1; i <= 10; i++) {
            TestData d = minMaxPQ.minPriorityItem();
            assert (d.priority == 1 && d.data == i);
        }
    }

    @Test
    public void testMixedPriorityWithSamePriorityData() {
        MinMaxPriorityQueue<TestData> minMaxPQ = new MinMaxPriorityQueueImpl<>();
        TestData[] data = new TestData[]{
                new TestData(1, 1),
                new TestData(2, 1),
                new TestData(1, 2),
                new TestData(2, 2),
                new TestData(1, 3),
                new TestData(1, 4),
                new TestData(1, 5),
                new TestData(2, 3),
                new TestData(2, 4),
                new TestData(2, 5),
                new TestData(3, 1),
                new TestData(3, 2),
                new TestData(3, 3),
                new TestData(3, 4),
                new TestData(3, 5)
        };

        for (TestData d : data) {
            minMaxPQ.add(d, d.priority);
        }

        for (int i = 1; i <= 5; i++) {
            TestData d = minMaxPQ.minPriorityItem();
            assert (d.priority == 1 && d.data == i);
            d = minMaxPQ.maxPriorityItem();
            assert (d.priority == 3 && d.data == i);
        }

        TestData d = minMaxPQ.minPriorityItem();
        assert (d.priority == 2 && d.data == 1);
        d = minMaxPQ.maxPriorityItem();
        assert (d.priority == 2 && d.data == 2);
    }
}

