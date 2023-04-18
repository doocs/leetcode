class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int x : nums) {
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