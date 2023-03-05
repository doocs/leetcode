class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> cnt = new HashMap<>();

    public DetectSquares() {
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        cnt.computeIfAbsent(x, k -> new HashMap<>()).merge(y, 1, Integer::sum);
    }

    public int count(int[] point) {
        int x1 = point[0], y1 = point[1];
        if (!cnt.containsKey(x1)) {
            return 0;
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x2 = e.getKey();
            if (x2 != x1) {
                int d = x2 - x1;
                var cnt1 = cnt.get(x1);
                var cnt2 = e.getValue();
                ans += cnt2.getOrDefault(y1, 0) * cnt1.getOrDefault(y1 + d, 0)
                    * cnt2.getOrDefault(y1 + d, 0);
                ans += cnt2.getOrDefault(y1, 0) * cnt1.getOrDefault(y1 - d, 0)
                    * cnt2.getOrDefault(y1 - d, 0);
            }
        }
        return ans;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */