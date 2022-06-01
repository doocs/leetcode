class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else {
                cnt += (m == v ? 1 : -1);
            }
        }
        cnt = 0;
        for (int v : nums) {
            if (m == v) {
                ++cnt;
            }
        }
        return cnt > nums.length / 2 ? m : -1;
    }
}