class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        final long inf = 1L << 62;
        Arrays.sort(packages);
        long ans = inf;
        for (var box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long s = 0;
            int i = 0;
            for (int b : box) {
                int j = search(packages, b, i);
                s += 1L * (j - i) * b;
                i = j;
            }
            ans = Math.min(ans, s);
        }
        if (ans == inf) {
            return -1;
        }
        long s = 0;
        for (int p : packages) {
            s += p;
        }
        final int mod = (int) 1e9 + 7;
        return (int) ((ans - s) % mod);
    }

    private int search(int[] nums, int x, int l) {
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}