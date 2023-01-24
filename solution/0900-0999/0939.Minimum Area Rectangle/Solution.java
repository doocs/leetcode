class Solution {
    public int minAreaRect(int[][] points) {
        TreeMap<Integer, List<Integer>> d = new TreeMap<>();
        for (var p : points) {
            int x = p[0], y = p[1];
            d.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
        }
        Map<Integer, Integer> pos = new HashMap<>();
        int ans = 1 << 30;
        for (var e : d.entrySet()) {
            int x = e.getKey();
            var ys = e.getValue();
            Collections.sort(ys);
            int n = ys.size();
            for (int i = 0; i < n; ++i) {
                int y1 = ys.get(i);
                for (int j = i + 1; j < n; ++j) {
                    int y2 = ys.get(j);
                    int p = y1 * 40001 + y2;
                    if (pos.containsKey(p)) {
                        ans = Math.min(ans, (x - pos.get(p)) * (y2 - y1));
                    }
                    pos.put(p, x);
                }
            }
        }
        return ans == 1 << 30 ? 0 : ans;
    }
}