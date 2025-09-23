public class Solution {
    public int MaxFrequencyElements(int[] nums) {
        int[] cnt = new int[101];
        foreach (int x in nums) {
            ++cnt[x];
        }
        int ans = 0, mx = -1;
        foreach (int x in cnt) {
            if (mx < x) {
                mx = x;
                ans = x;
            } else if (mx == x) {
                ans += x;
            }
        }
        return ans;
    }
}
