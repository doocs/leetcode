class RecentCounter {
    private Deque<Integer> q = new ArrayDeque<>();

    public RecentCounter() {
        
    }
    
    public int ping(int t) {
        q.offer(t);
        while (q.peekFirst() < t - 3000) {
            q.pollFirst();
        }
        return q.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */