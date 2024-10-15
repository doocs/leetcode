class Solution {
    private final TreeMap<Integer, Integer> l = new TreeMap<>();
    private final TreeMap<Integer, Integer> r = new TreeMap<>();
    private long s;
    private int size;

    public long minimumCost(int[] nums, int k, int dist) {
        --k;
        s = nums[0];
        for (int i = 1; i < dist + 2; ++i) {
            s += nums[i];
            l.merge(nums[i], 1, Integer::sum);
        }
        size = dist + 1;
        while (size > k) {
            l2r();
        }
        long ans = s;
        for (int i = dist + 2; i < nums.length; ++i) {
            int x = nums[i - dist - 1];
            if (l.containsKey(x)) {
                if (l.merge(x, -1, Integer::sum) == 0) {
                    l.remove(x);
                }
                s -= x;
                --size;
            } else if (r.merge(x, -1, Integer::sum) == 0) {
                r.remove(x);
            }
            int y = nums[i];
            if (y < l.lastKey()) {
                l.merge(y, 1, Integer::sum);
                ++size;
                s += y;
            } else {
                r.merge(y, 1, Integer::sum);
            }
            while (size < k) {
                r2l();
            }
            while (size > k) {
                l2r();
            }
            ans = Math.min(ans, s);
        }
        return ans;
    }

    private void l2r() {
        int x = l.lastKey();
        s -= x;
        if (l.merge(x, -1, Integer::sum) == 0) {
            l.remove(x);
        }
        --size;
        r.merge(x, 1, Integer::sum);
    }

    private void r2l() {
        int x = r.firstKey();
        if (r.merge(x, -1, Integer::sum) == 0) {
            r.remove(x);
        }
        l.merge(x, 1, Integer::sum);
        s += x;
        ++size;
    }
}
