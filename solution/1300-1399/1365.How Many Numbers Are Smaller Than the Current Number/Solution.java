class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = search(arr, nums[i]);
        }
        return nums;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}