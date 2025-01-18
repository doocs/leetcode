class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var row : wall) {
            int s = 0;
            for (int i = 0; i + 1 < row.size(); ++i) {
                s += row.get(i);
                cnt.merge(s, 1, Integer::sum);
            }
        }
        int mx = 0;
        for (var x : cnt.values()) {
            mx = Math.max(mx, x);
        }
        return wall.size() - mx;
    }
}
