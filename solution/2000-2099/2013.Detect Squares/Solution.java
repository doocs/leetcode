class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();

    public DetectSquares() {

    }
    
    public void add(int[] point) {
        int x = point[0], y = point[1];
        if (!mp.containsKey(x)) {
            mp.put(x, new HashMap<>());
        }
        mp.get(x).put(y, mp.get(x).getOrDefault(y, 0) + 1);
    }
    
    public int count(int[] point) {
        int x = point[0], y = point[1];
        int ans = 0;
        if (!mp.containsKey(x)) {
            return ans;
        }
        Map<Integer, Integer> xcnt = mp.get(x);
        for (Map.Entry<Integer, Map<Integer, Integer>> e : mp.entrySet()) {
            int x1 = e.getKey();
            Map<Integer, Integer> counter = e.getValue();
            if (x1 != x) {
                int d = x1 - x;
                ans += xcnt.getOrDefault(y + d, 0) * counter.getOrDefault(y, 0) * counter.getOrDefault(y + d, 0);
                ans += xcnt.getOrDefault(y - d, 0) * counter.getOrDefault(y, 0) * counter.getOrDefault(y - d, 0);
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