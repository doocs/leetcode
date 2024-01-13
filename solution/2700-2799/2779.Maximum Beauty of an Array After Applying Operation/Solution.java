class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int m = Arrays.stream(nums).max().getAsInt() + k * 2 + 2;
        int[] d = new int[m];
        for (int x : nums) {
            d[x]++;
            d[x + k * 2 + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}