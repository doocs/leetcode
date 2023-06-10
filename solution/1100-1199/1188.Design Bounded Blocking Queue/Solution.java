class BoundedBlockingQueue {
    private Semaphore s1;
    private Semaphore s2;
    private Deque<Integer> q = new ArrayDeque<>();

    public BoundedBlockingQueue(int capacity) {
        s1 = new Semaphore(capacity);
        s2 = new Semaphore(0);
    }

    public void enqueue(int element) throws InterruptedException {
        s1.acquire();
        q.offer(element);
        s2.release();
    }

    public int dequeue() throws InterruptedException {
        s2.acquire();
        int ans = q.poll();
        s1.release();
        return ans;
    }

    public int size() {
        return q.size();
    }
}