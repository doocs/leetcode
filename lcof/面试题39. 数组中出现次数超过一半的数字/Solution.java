class Solution {
    public int majorityElement(int[] nums) {
        int major = 0, cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                major = num;
                ++cnt;
            } else {
                cnt += (num == major ? 1 : -1);
            }
        }
        return major;
    }
}