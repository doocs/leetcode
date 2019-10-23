class Solution {
    public int removeElement(int[] nums, int val) {
        int limit = 0, length = nums.length;
        for (int i = 0, j = 0; i < length - limit; i ++) {
            while (j < length && nums[j ++] == val) {
                limit ++;
            }
            if (i + limit < length) {
                nums[i] = nums[i + limit];
            }
        }
        return length - limit;
    }
}