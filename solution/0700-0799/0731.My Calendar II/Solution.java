class MyCalendarTwo {
    private Map<Integer, Integer> tm = new TreeMap<>();

    public MyCalendarTwo() {
    }

    public boolean book(int start, int end) {
        tm.put(start, tm.getOrDefault(start, 0) + 1);
        tm.put(end, tm.getOrDefault(end, 0) - 1);
        int s = 0;
        for (int v : tm.values()) {
            s += v;
            if (s > 2) {
                tm.put(start, tm.get(start) - 1);
                tm.put(end, tm.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */