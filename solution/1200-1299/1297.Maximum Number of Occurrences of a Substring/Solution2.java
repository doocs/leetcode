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
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        Hashing hashing = new Hashing(s);
        int[] freq = new int[256];
        int k = 0;
        int ans = 0;
        Map<Long, Integer> cnt = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            if (++freq[s.charAt(i - 1)] == 1) {
                k++;
            }

            if (i >= minSize) {
                if (k <= maxLetters) {
                    long x = hashing.query(i - minSize + 1, i);
                    ans = Math.max(ans, cnt.merge(x, 1, Integer::sum));
                }
                int j = i - minSize;
                if (--freq[s.charAt(j)] == 0) {
                    k--;
                }
            }
        }

        return ans;
    }
}
