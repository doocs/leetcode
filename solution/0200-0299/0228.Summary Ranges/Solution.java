class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0, j = 0, n = nums.length; j < n;) {
            while (j + 1 < n && nums[j] + 1 == nums[j + 1]) {
                ++j;
            }
            res.add(make(nums, i, j));
            i = j + 1;
            j = i;
        }
        return res;
    }

    private String make(int[] nums, int i, int j) {
        return i == j ? String.valueOf(nums[i]) : String.format("%d->%d", nums[i], nums[j]);
    }
}