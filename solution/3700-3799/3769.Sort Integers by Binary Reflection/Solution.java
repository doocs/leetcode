class Solution {
    public int[] sortByReflection(int[] nums) {
        int n = nums.length;
        Integer[] a = new Integer[n];
        Arrays.setAll(a, i -> nums[i]);

        Arrays.sort(a, (u, v) -> {
            int fu = f(u);
            int fv = f(v);
            if (fu != fv) {
                return Integer.compare(fu, fv);
            }
            return Integer.compare(u, v);
        });

        for (int i = 0; i < n; i++) nums[i] = a[i];
        return nums;
    }

    private int f(int x) {
        int y = 0;
        while (x != 0) {
            y = (y << 1) | (x & 1);
            x >>= 1;
        }
        return y;
    }
}
