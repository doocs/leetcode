class Hashing {
    private final long[] p;
    private final long[] h;
    private final long mod;

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
    public int minimumCost(String target, String[] words, int[] costs) {
        final int base = 13331;
        final int mod = 998244353;
        final int inf = Integer.MAX_VALUE / 2;

        int n = target.length();
        Hashing hashing = new Hashing(target, base, mod);

        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        TreeSet<Integer> ss = new TreeSet<>();
        for (String w : words) {
            ss.add(w.length());
        }

        Map<Long, Integer> d = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            long x = 0;
            for (char c : words[i].toCharArray()) {
                x = (x * base + c) % mod;
            }
            d.merge(x, costs[i], Integer::min);
        }

        for (int i = 1; i <= n; i++) {
            for (int j : ss) {
                if (j > i) {
                    break;
                }
                long x = hashing.query(i - j + 1, i);
                f[i] = Math.min(f[i], f[i - j] + d.getOrDefault(x, inf));
            }
        }

        return f[n] >= inf ? -1 : f[n];
    }
}