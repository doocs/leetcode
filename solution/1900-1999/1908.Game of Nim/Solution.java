class Solution {
    private Map<Integer, Boolean> memo = new HashMap<>();
    private int[] p = new int[8];

    public Solution() {
        p[0] = 1;
        for (int i = 1; i < 8; ++i) {
            p[i] = p[i - 1] * 8;
        }
    }

    public boolean nimGame(int[] piles) {
        return dfs(piles);
    }

    private boolean dfs(int[] piles) {
        int st = f(piles);
        if (memo.containsKey(st)) {
            return memo.get(st);
        }
        for (int i = 0; i < piles.length; ++i) {
            for (int j = 1; j <= piles[i]; ++j) {
                piles[i] -= j;
                if (!dfs(piles)) {
                    piles[i] += j;
                    memo.put(st, true);
                    return true;
                }
                piles[i] += j;
            }
        }
        memo.put(st, false);
        return false;
    }

    private int f(int[] piles) {
        int st = 0;
        for (int i = 0; i < piles.length; ++i) {
            st += piles[i] * p[i];
        }
        return st;
    }
}