class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private int size;

    public int maxHappyGroups(int batchSize, int[] groups) {
        size = batchSize;
        int ans = 0;
        long state = 0;
        for (int g : groups) {
            int i = g % size;
            if (i == 0) {
                ++ans;
            } else {
                state += 1l << (i * 5);
            }
        }
        ans += dfs(state, 0);
        return ans;
    }

    private int dfs(long state, int mod) {
        if (f.containsKey(state)) {
            return f.get(state);
        }
        int res = 0;
        for (int i = 1; i < size; ++i) {
            if ((state >> (i * 5) & 31) != 0) {
                int t = dfs(state - (1l << (i * 5)), (mod + i) % size);
                res = Math.max(res, t + (mod == 0 ? 1 : 0));
            }
        }
        f.put(state, res);
        return res;
    }
}