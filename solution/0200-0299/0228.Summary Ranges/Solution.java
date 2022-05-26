class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0, j = 1; i < nums.length; i = j++) {
            while (j < nums.length && nums[j] - nums[j - 1] == 1) {
                ++j;
            }
            if (j - i == 1) {
                res.add(String.valueOf(nums[i]));
            } else {
                res.add(nums[i] + "->" + nums[j - 1]);
            }
        }
        return res;
    }
}
