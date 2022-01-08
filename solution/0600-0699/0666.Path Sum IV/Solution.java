class Solution {
    private int ans;
    private Map<Integer, Integer> mp;

    public int pathSum(int[] nums) {
        ans = 0;
        mp = new HashMap<>(nums.length);
        for (int num : nums) {
            mp.put(num / 10, num % 10);
        }
        dfs(11, 0);
        return ans;
    }

    private void dfs(int node, int t) {
        if (!mp.containsKey(node)) {
            return;
        }
        t += mp.get(node);
        int d = node / 10, p = node % 10;
        int l = (d + 1) * 10 + (p * 2) - 1;
        int r = l + 1;
        if (!mp.containsKey(l) && !mp.containsKey(r)) {
            ans += t;
            return;
        }
        dfs(l, t);
        dfs(r, t);
    }
}