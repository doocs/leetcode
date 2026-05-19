class Solution {
    private void add(TreeMap<Integer, Integer> t, int x) {
        t.merge(x, 1, Integer::sum);
    }

    private void remove(TreeMap<Integer, Integer> t, int x) {
        int v = t.get(x);

        if (v == 1) {
            t.remove(x);
        } else {
            t.put(x, v - 1);
        }
    }

    private long qpow(long a, int b, int mod) {
        long ans = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                ans = ans * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return ans;
    }

    public List<Integer> powerUpdate(int[] nums, int p, int[][] queries) {
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();

        int sz1 = 0, sz2 = nums.length;

        for (int x : nums) {
            add(r, x);
        }

        int mod = 1_000_000_007;

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int val = q[0];
            int k = q[1];

            add(r, val);
            ++sz2;

            int v = r.firstKey();

            remove(r, v);
            --sz2;

            add(l, v);
            ++sz1;

            while (sz2 < k) {
                v = l.lastKey();

                remove(l, v);
                --sz1;

                add(r, v);
                ++sz2;
            }

            while (sz2 > k) {
                v = r.firstKey();

                remove(r, v);
                --sz2;

                add(l, v);
                ++sz1;
            }

            int x = r.firstKey();

            p = (int) qpow(p, x, mod);

            ans.add(p);
        }

        return ans;
    }
}