class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int p = i + 1, q = n - 1;
            while (p < q) {
                if (p > i + 1 && nums[p] == nums[p - 1]) {
                    ++p;
                    continue;
                }
                if (q < n - 1 && nums[q] == nums[q + 1]) {
                    --q;
                    continue;
                }
                if (nums[p] + nums[q] + nums[i] < 0) {
                    ++p;
                } else if (nums[p] + nums[q] + nums[i] > 0) {
                    --q;
                } else {
                    res.add(Arrays.asList(nums[p], nums[q], nums[i]));
                    ++p;
                    --q;
                }
            }
        }
        return res;
    }
}