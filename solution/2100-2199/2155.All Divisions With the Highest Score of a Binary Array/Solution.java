class Solution {
    public List<Integer> maxScoreIndices(int[] nums) {
        int left = 0, right = sum(nums);
        int mx = right;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++left;
            } else {
                --right;
            }
            int t = left + right;
            if (mx == t) {
                ans.add(i + 1);
            } else if (mx < t) {
                mx = t;
                ans.clear();
                ans.add(i + 1);
            }
        }
        return ans;
    }

    private int sum(int[] nums) {
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        return s;
    }
}