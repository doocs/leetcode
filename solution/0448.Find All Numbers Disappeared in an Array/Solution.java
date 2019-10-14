class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        boolean[] inx = new boolean[nums.length + 1];
        for (int num : nums) {
            inx[num] = true;
        }
        for (int i = 1, length = nums.length; i <= length; i++) {
            if (!inx[i]) {
                result.add(i);
            }
        }
        return result;
    }
}