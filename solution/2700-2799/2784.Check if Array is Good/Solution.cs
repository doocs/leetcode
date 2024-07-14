public class Solution {
    public bool IsGood(int[] nums) {
        int n = nums.Length - 1;
        int[] cnt = new int[201];
        foreach (int x in nums) {
            ++cnt[x];
        }
        if (cnt[n] != 2) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (cnt[i] != 1) {
                return false;
            }
        }
        return true;
    }
}