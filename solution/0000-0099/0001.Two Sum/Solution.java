class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            int x = target - v;
            if (m.containsKey(x)) {
                return new int[]{m.get(x), i};
            }
            m.put(v, i);
        }
        return null;
    }
}