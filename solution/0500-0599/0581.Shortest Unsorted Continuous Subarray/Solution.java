class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int l = 0, r = arr.length - 1;
        while (l <= r && nums[l] == arr[l]) {
            l++;
        }
        while (l <= r && nums[r] == arr[r]) {
            r--;
        }
        return r - l + 1;
    }
}