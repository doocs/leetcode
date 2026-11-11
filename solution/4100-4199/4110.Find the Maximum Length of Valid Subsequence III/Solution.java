class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        Map<Integer, Integer> last = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            int y = (k - x) % k;
            if (last.containsKey(y)) {
                int j = last.get(y);
                f[i] = Math.max(f[i], f[j] + 1);
                ans = Math.max(ans, f[i]);
            }
            last.put(x, i);
        }
        return ans;
    }
}