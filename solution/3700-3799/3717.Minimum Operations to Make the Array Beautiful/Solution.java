class Solution {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> f = new HashMap<>();
        f.put(nums[0], 0);

        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            Map<Integer, Integer> g = new HashMap<>();

            for (var entry : f.entrySet()) {
                int pre = entry.getKey();
                int s = entry.getValue();

                int cur = (x + pre - 1) / pre * pre;
                while (cur <= 100) {
                    int val = s + (cur - x);
                    if (!g.containsKey(cur) || g.get(cur) > val) {
                        g.put(cur, val);
                    }
                    cur += pre;
                }
            }
            f = g;
        }

        int ans = Integer.MAX_VALUE;
        for (int v : f.values()) {
            ans = Math.min(ans, v);
        }
        return ans;
    }
}
