public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, m = 0;
        foreach (int x in nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
}