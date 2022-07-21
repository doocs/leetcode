class Solution {
    private int n;
    private Map<Long, Boolean> memo = new HashMap<>();

    public boolean canWin(String currentState) {
        long mask = 0;
        n = currentState.length();
        for (int i = 0; i < n; ++i) {
            if (currentState.charAt(i) == '+') {
                mask |= 1 << i;
            }
        }
        return dfs(mask);
    }

    private boolean dfs(long mask) {
        if (memo.containsKey(mask)) {
            return memo.get(mask);
        }
        for (int i = 0; i < n - 1; ++i) {
            if ((mask & (1 << i)) == 0 || (mask & (1 << (i + 1))) == 0) {
                continue;
            }
            if (dfs(mask ^ (1 << i) ^ (1 << (i + 1)))) {
                continue;
            }
            memo.put(mask, true);
            return true;
        }
        memo.put(mask, false);
        return false;
    }
}