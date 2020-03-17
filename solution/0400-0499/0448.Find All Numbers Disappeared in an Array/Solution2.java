class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0, length = nums.length; i < length; i++) {
            int abs = Math.abs(nums[i]);
            nums[abs - 1] = -Math.abs(nums[abs - 1]);
        }
        for (int i = 0, length = nums.length; i < length; i++) {
            if (nums[i] > 0) {
                result.add(i+1);
            }
        }
        return result;
    }
}