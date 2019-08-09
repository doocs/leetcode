class Solution {
    public int stoneGameII(int[] piles) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = Arrays.stream(piles).sum();
        return (total + dfs(0, 1, piles, map)) >> 1;
    }

    private int dfs(int s, int M, int[] piles, Map<Integer, Integer> map) {
        if (s >= piles.length) {
            return 0;
        }
        int key = s << 8 | M;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (s + 2 * M >= piles.length) {
            int res = 0;
            for (int i = s; i < piles.length; ++i) {
                res += piles[i];
            }
            return res;
        }
        int best = Integer.MIN_VALUE;
        int cur = 0;
        for (int x = 1; x <= 2 * M && s + x - 1 < piles.length; ++x) {
            cur += piles[s + x - 1];
            best = Math.max(best, cur - dfs(s + x, Math.max(x, M), piles, map));
        }
        map.put(key, best);
        return best;
    }
}
