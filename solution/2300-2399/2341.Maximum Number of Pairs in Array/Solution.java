class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int v : nums) {
            ++cnt[v];
        }
        int s = 0;
        for (int v : cnt) {
            s += v / 2;
        }
        return new int[]{s, nums.length - s * 2};
    }
}