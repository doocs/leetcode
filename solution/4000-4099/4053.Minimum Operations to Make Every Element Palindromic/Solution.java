class Solution {
    static List<Long>[] ps = new ArrayList[2];

    static {
        ps[0] = new ArrayList<>();
        ps[1] = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            String s = String.valueOf(i);
            String t1 = new StringBuilder(s).reverse().toString();
            String t2 = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
            long x = Long.parseLong(s + t1);
            ps[(int) (x & 1)].add(x);
            long y = Long.parseLong(s + t2);
            ps[(int) (y & 1)].add(y);
        }
        ps[0].sort(Long::compare);
        ps[1].sort(Long::compare);
    }

    public long minOperations(int[] nums) {
        long ans = 0;
        for (int x : nums) {
            List<Long> p = ps[x & 1];
            int l = 0, r = p.size();
            while (l < r) {
                int m = (l + r) >>> 1;
                if (p.get(m) < x) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            long t = Long.MAX_VALUE;
            if (l < p.size()) {
                t = p.get(l) - x;
            }
            if (l > 0) {
                t = Math.min(t, (long) x - p.get(l - 1));
            }
            ans += t / 2;
        }
        return ans;
    }
}
