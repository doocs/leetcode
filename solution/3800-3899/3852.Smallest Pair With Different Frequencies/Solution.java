class Solution {
    public int[] minDistinctFreqPair(int[] nums) {
        final int inf = Integer.MAX_VALUE;
        Map<Integer, Integer> cnt = new HashMap<>();
        int x = inf;
        for (int v : nums) {
            cnt.merge(v, 1, Integer::sum);
            x = Math.min(x, v);
        }
        int minY = inf;
        for (int y : cnt.keySet()) {
            if (y < minY && cnt.get(x) != cnt.get(y)) {
                minY = y;
            }
        }
        return minY == inf ? new int[] {-1, -1} : new int[] {x, minY};
    }
}
