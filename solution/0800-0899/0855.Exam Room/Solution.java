class ExamRoom {
    private TreeSet<int[]> ts = new TreeSet<>((a, b) -> {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a[0] - b[0] : d2 - d1;
    });
    private Map<Integer, Integer> left = new HashMap<>();
    private Map<Integer, Integer> right = new HashMap<>();
    private int n;

    public ExamRoom(int n) {
        this.n = n;
        add(new int[] {-1, n});
    }

    public int seat() {
        int[] s = ts.first();
        int p = (s[0] + s[1]) >> 1;
        if (s[0] == -1) {
            p = 0;
        } else if (s[1] == n) {
            p = n - 1;
        }
        del(s);
        add(new int[] {s[0], p});
        add(new int[] {p, s[1]});
        return p;
    }

    public void leave(int p) {
        int l = left.get(p), r = right.get(p);
        del(new int[] {l, p});
        del(new int[] {p, r});
        add(new int[] {l, r});
    }

    private int dist(int[] s) {
        int l = s[0], r = s[1];
        return l == -1 || r == n ? r - l - 1 : (r - l) >> 1;
    }

    private void add(int[] s) {
        ts.add(s);
        left.put(s[1], s[0]);
        right.put(s[0], s[1]);
    }

    private void del(int[] s) {
        ts.remove(s);
        left.remove(s[1]);
        right.remove(s[0]);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */