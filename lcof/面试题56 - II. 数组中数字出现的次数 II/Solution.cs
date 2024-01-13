public class Solution {
    public int SingleNumber(int[] nums) {
        int[] cnt = new int[32];
        foreach(int x in nums) {
            int v = x;
            for (int i = 0; i < 32; ++i) {
                cnt[i] += v & 1;
                v >>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            if (cnt[i] % 3 == 1) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
