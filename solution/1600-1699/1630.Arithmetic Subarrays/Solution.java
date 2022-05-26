class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; ++i) {
            res.add(check(nums, l[i], r[i]));
        }
        return res;
    }

    private boolean check(int[] nums, int l, int r) {
        if (r - l < 2) {
            return true;
        }
        Set<Integer> s = new HashSet<>();
        int mx = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int i = l; i <= r; ++i) {
            s.add(nums[i]);
            mx = Math.max(mx, nums[i]);
            mi = Math.min(mi, nums[i]);
        }
        if ((mx - mi) % (r - l) != 0) {
            return false;
        }
        int delta = (mx - mi) / (r - l);
        for (int i = 1; i <= r - l; ++i) {
            if (!s.contains(mi + delta * i)) {
                return false;
            }
        }
        return true;
    }
}