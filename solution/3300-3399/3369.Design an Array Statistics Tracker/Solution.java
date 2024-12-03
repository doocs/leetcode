class MedianFinder {
    private final PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> large = new PriorityQueue<>();
    private final Map<Integer, Integer> delayed = new HashMap<>();
    private int smallSize;
    private int largeSize;

    public void addNum(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        rebalance();
    }

    public Integer findMedian() {
        return smallSize == largeSize ? large.peek() : small.peek();
    }

    public void removeNum(int num) {
        delayed.merge(num, 1, Integer::sum);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        rebalance();
    }

    private void prune(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty() && delayed.containsKey(pq.peek())) {
            if (delayed.merge(pq.peek(), -1, Integer::sum) == 0) {
                delayed.remove(pq.peek());
            }
            pq.poll();
        }
    }

    private void rebalance() {
        if (smallSize > largeSize + 1) {
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < largeSize) {
            small.offer(large.poll());
            --largeSize;
            ++smallSize;
            prune(large);
        }
    }
}

class StatisticsTracker {
    private final Deque<Integer> q = new ArrayDeque<>();
    private long s;
    private final Map<Integer, Integer> cnt = new HashMap<>();
    private final MedianFinder medianFinder = new MedianFinder();
    private final TreeSet<int[]> ts
        = new TreeSet<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);

    public StatisticsTracker() {
    }

    public void addNumber(int number) {
        q.offerLast(number);
        s += number;
        ts.remove(new int[] {number, cnt.getOrDefault(number, 0)});
        cnt.merge(number, 1, Integer::sum);
        medianFinder.addNum(number);
        ts.add(new int[] {number, cnt.get(number)});
    }

    public void removeFirstAddedNumber() {
        int number = q.pollFirst();
        s -= number;
        ts.remove(new int[] {number, cnt.get(number)});
        cnt.merge(number, -1, Integer::sum);
        medianFinder.removeNum(number);
        ts.add(new int[] {number, cnt.get(number)});
    }

    public int getMean() {
        return (int) (s / q.size());
    }

    public int getMedian() {
        return medianFinder.findMedian();
    }

    public int getMode() {
        return ts.first()[0];
    }
}
