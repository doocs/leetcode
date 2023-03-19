class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; ++i) {
            ans.add(check(nums, l[i], r[i]));
        }
        return ans;
    }

    private boolean check(int[] nums, int l, int r) {
        Set<Integer> s = new HashSet<>();
        int n = r - l + 1;
        int a1 = 1 << 30, an = -a1;
        for (int i = l; i <= r; ++i) {
            s.add(nums[i]);
            a1 = Math.min(a1, nums[i]);
            an = Math.max(an, nums[i]);
        }
        if ((an - a1) % (n - 1) != 0) {
            return false;
        }
        int d = (an - a1) / (n - 1);
        for (int i = 1; i < n; ++i) {
            if (!s.contains(a1 + (i - 1) * d)) {
                return false;
            }
        }
        return true;
    }
}