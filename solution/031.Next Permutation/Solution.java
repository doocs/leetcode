class Solution {
    public void nextPermutation(int[] nums) {
        boolean flag = false;
        for (int i = nums.length - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                int index = findMinIndex(nums, i, nums[i]);
                swap(nums, i, index);
                reverse(nums, i + 1);
                flag = true;
                break;
            }
        }
        if (!flag) {
            Arrays.sort(nums);
        }
    }
    
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
    
    /**
     * 找出从start开始的比val大的最小元素的下标，如果有多个，选择后者 
     *
     * @param name
     * @param start
     * @param val
     * @return index
     */
    private int findMinIndex(int[] nums, int start, int val) {
        int end = nums.length - 1;
        int i = start;
        for (; i < end; ++i) {
            if (nums[i + 1] <= val) {
                break;
            }
        }
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}