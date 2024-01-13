class Solution {
    public int getKth(int lo, int hi, int k) {
        Integer[] nums = new Integer[hi - lo + 1];
        for (int i = lo; i <= hi; ++i) {
            nums[i - lo] = i;
        }
        Arrays.sort(nums, (a, b) -> {
            int fa = f(a), fb = f(b);
            return fa == fb ? a - b : fa - fb;
        });
        return nums[k - 1];
    }

    private int f(int x) {
        int ans = 0;
        for (; x != 1; ++ans) {
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = x * 3 + 1;
            }
        }
        return ans;
    }
}