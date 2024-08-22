class Solution {
    public List<Integer> maxScoreIndices(int[] nums) {
        int l0 = 0, r1 = Arrays.stream(nums).sum();
        int mx = r1;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 1; i <= nums.length; ++i) {
            int x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            int t = l0 + r1;
            if (mx == t) {
                ans.add(i);
            } else if (mx < t) {
                mx = t;
                ans.clear();
                ans.add(i);
            }
        }
        return ans;
    }
}