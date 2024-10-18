class Solution {
    public int[] numsGame(int[] nums) {
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();
        int n = nums.length;
        int[] ans = new int[n];
        final int mod = (int) 1e9 + 7;
        long s = 0, t = 0;
        int lSize = 0, rSize = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i] - i;
            r.merge(x, 1, Integer::sum);
            t += x;
            x = r.firstKey();
            if (r.merge(x, -1, Integer::sum) == 0) {
                r.remove(x);
            }
            t -= x;
            l.merge(x, 1, Integer::sum);
            s += x;
            ++lSize;
            while (lSize - rSize > 1) {
                x = l.lastKey();
                if (l.merge(x, -1, Integer::sum) == 0) {
                    l.remove(x);
                }
                s -= x;
                --lSize;
                r.merge(x, 1, Integer::sum);
                t += x;
                ++rSize;
            }
            long mid = l.lastKey();
            ans[i] = (int) ((mid * lSize - s + t - mid * rSize) % mod);
        }
        return ans;
    }
}
