public class Solution {
    public int SingleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 0;
            foreach (var num in nums) {
                bit += ((num >> i) & 1);
            }
            res += ((bit % 3) << i);
        }
        return res;
    }
}