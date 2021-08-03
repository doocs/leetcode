class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] numsSorted = new int[n];
        System.arraycopy(nums, 0, numsSorted, 0, n);
        Arrays.sort(numsSorted);
        int left = 0, right = n - 1;
        while (left < n && nums[left] == numsSorted[left]) {
            left++;
        }
        while (right >= 0 && nums[right] == numsSorted[right]) {
            right--;
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
