class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> mask = new HashMap<>();
        int[][] nums = {nums1, nums2, nums3};
        for (int i = 0; i < 3; i++) {
            for (int x : nums[i]) {
                mask.merge(x, 1 << i, (oldVal, newVal) -> oldVal | newVal);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (var e : mask.entrySet()) {
            if ((e.getValue() & (e.getValue() - 1)) != 0) {
                ans.add(e.getKey());
            }
        }
        return ans;
    }
}
