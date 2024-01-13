class Solution {
    public int maximizeSweetness(int[] sweetness, int k) {
        int l = 0, r = 0;
        for (int v : sweetness) {
            r += v;
        }
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(sweetness, mid, k)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int x, int k) {
        int s = 0, cnt = 0;
        for (int v : nums) {
            s += v;
            if (s >= x) {
                s = 0;
                ++cnt;
            }
        }
        return cnt > k;
    }
}