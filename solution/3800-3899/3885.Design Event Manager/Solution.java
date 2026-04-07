class EventManager {
    private TreeSet<int[]> sl;
    private Map<Integer, Integer> d;

    public EventManager(int[][] events) {
        sl = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        d = new HashMap<>();
        for (int[] e : events) {
            int eventId = e[0], priority = e[1];
            sl.add(new int[] {-priority, eventId});
            d.put(eventId, priority);
        }
    }

    public void updatePriority(int eventId, int newPriority) {
        int old = d.get(eventId);
        sl.remove(new int[] {-old, eventId});
        sl.add(new int[] {-newPriority, eventId});
        d.put(eventId, newPriority);
    }

    public int pollHighest() {
        if (sl.isEmpty()) {
            return -1;
        }
        int[] top = sl.pollFirst();
        int eventId = top[1];
        d.remove(eventId);
        return eventId;
    }
}

/**
 * Your EventManager object will be instantiated and called as such:
 * EventManager obj = new EventManager(events);
 * obj.updatePriority(eventId,newPriority);
 * int param_2 = obj.pollHighest();
 */
