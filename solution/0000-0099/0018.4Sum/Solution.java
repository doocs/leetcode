class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n;
        if (nums == null || (n = (nums.length)) < 4) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int p = j + 1, q = n - 1;
                while (p < q) {
                    if (p > j + 1 && nums[p] == nums[p - 1]) {
                        ++p;
                        continue;
                    }
                    if (q < n - 1 && nums[q] == nums[q + 1]) {
                        --q;
                        continue;
                    }
                    int t = nums[i] + nums[j] + nums[p] + nums[q];
                    if (t == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
                        ++p;
                        --q;
                    } else if (t < target) {
                        ++p;
                    } else {
                        --q;
                    }
                }
            }
        }
        return res;
    }
}