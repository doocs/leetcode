class Hashing {
    private final long[] p;
    private final long[] h;
    private final long mod;

    public Hashing(String word) {
        this(word, 13331, 998244353);
    }

    public Hashing(String word, long base, int mod) {
        int n = word.length();
        p = new long[n + 1];
        h = new long[n + 1];
        p[0] = 1;
        this.mod = mod;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * base % mod;
            h[i] = (h[i - 1] * base + word.charAt(i - 1)) % mod;
        }
    }

    public long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
}

class Solution {
    public List<String> partitionString(String s) {
        Hashing hashing = new Hashing(s);
        Set<Long> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (int l = 1, r = 1; r <= s.length(); ++r) {
            long x = hashing.query(l, r);
            if (vis.add(x)) {
                ans.add(s.substring(l - 1, r));
                l = r + 1;
            }
        }
        return ans;
    }
}