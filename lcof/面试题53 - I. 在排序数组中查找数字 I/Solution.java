class Solution {
    private int[] nums;

    public int search(int[] nums, int target) {
        this.nums = nums;
        int l = search(target);
        int r = search(target + 1);
        return r - l;
    }

    private int search(int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}