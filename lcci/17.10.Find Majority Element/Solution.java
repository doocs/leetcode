class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        for (int num : nums) {
            if (cnt == 0) {
                candidate = num;
            }
            cnt += (num == candidate ? 1 : -1);
        }
        cnt = 0;
        for (int num : nums) {
            if (num == candidate) {
                ++cnt;
            }
        }
        return cnt > nums.length / 2 ? candidate : -1;
    }
}