class Solution {
    public String nearestPalindromic(String n) {
        long x = Long.parseLong(n);
        long ans = -1;
        for (long t : get(n)) {
            if (ans == -1 || Math.abs(t - x) < Math.abs(ans - x) || (Math.abs(t - x) == Math.abs(ans - x) && t < ans)) {
                ans = t;
            }
        }
        return Long.toString(ans);
    }

    private Set<Long> get(String n) {
        int l = n.length();
        Set<Long> res = new HashSet<>();
        res.add((long) Math.pow(10, l - 1) - 1);
        res.add((long) Math.pow(10, l) + 1);
        long left = Long.parseLong(n.substring(0, (l + 1) / 2));
        for (long i = left - 1; i <= left + 1; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(new StringBuilder(i + "").reverse().substring(l & 1));
            res.add(Long.parseLong(sb.toString()));
        }
        res.remove(Long.parseLong(n));
        return res;
    }
}