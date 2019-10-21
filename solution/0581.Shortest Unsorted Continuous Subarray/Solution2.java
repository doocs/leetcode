class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] numArrs = nums.clone();
        Arrays.sort(numArrs);

        int start = numArrs.length, end = 0;
        for (int i = 0; i < numArrs.length; i++) {
            if (numArrs[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }
}