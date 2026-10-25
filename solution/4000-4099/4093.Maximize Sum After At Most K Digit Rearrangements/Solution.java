class Solution {
    public long maximizeSum(int[] nums, int k) {
        int n = nums.length;
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int[] cnt = new int[10];
            while (x > 0) {
                int v = x % 10;
                x /= 10;
                cnt[v]++;
            }
            int y = 0;
            for (int j = 9; j >= 0; j--) {
                for (int c = 0; c < cnt[j]; c++) {
                    y = y * 10 + j;
                }
            }
            t[i] = y;
        }
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (i, j) -> Integer.compare(t[j] - nums[j], t[i] - nums[i]));
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[ids[i]];
            ans += i < k ? t[ids[i]] : x;
        }
        return ans;
    }
}