class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int p = 0;
        int q = n - 1;

        int mid = p + ((q - p) >> 1);

        while (p < q) {
            if (nums[p] <= nums[q]) {
                break;
            }

            if (nums[p] > nums[mid]) {
                q = mid;
            } else {
                p = mid + 1;
            }

            mid = p + ((q - p) >> 1);
        }

        return nums[p];

    }
}