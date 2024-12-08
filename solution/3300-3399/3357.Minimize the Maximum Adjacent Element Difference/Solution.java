public class Solution {

    public int minimizeMaxDiff(int[] nums) {
        int left = 0, right = (int) 1e9, result = (int) 1e9;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private boolean isValid(int[] nums, int maxDiff) {
        int prev = nums[0] != -1 ? nums[0] : -1;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (current == -1) {
                if (prev != -1) {
                    current = Math.max(prev - maxDiff, 1);
                } else {
                    current = 1;
                }
            }
            if (prev != -1 && Math.abs(current - prev) > maxDiff) {
                return false;
            }
            prev = current;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        int[] nums1 = {1, 2, -1, 10, 8};
        System.out.println(solver.minimizeMaxDiff(nums1));

        int[] nums2 = {-1, -1, -1};
        System.out.println(solver.minimizeMaxDiff(nums2));

        int[] nums3 = {-1, 10, -1, 8};
        System.out.println(solver.minimizeMaxDiff(nums3));
    }
}
