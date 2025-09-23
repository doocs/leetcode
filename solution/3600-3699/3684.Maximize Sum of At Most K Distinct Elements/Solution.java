class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; --i) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                continue;
            }
            ans.add(nums[i]);
            if (--k == 0) {
                break;
            }
        }
        return ans.stream().mapToInt(x -> x).toArray();
    }
}
