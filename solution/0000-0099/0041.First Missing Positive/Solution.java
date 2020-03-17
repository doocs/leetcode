class Solution {
    public int firstMissingPositive(int[] nums) {
        int re = 1;
        Arrays.sort(nums);
        for (int num : nums) {
            if (num == re) re++;
            if (num > re) return re;
        }
        return re;
    }
}