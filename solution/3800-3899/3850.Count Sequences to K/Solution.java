class Solution {

    record State(int i, long p, long q) {
    }

    private Map<State, Integer> f;
    private int[] nums;
    private int n;
    private long k;

    public int countSequences(int[] nums, long k) {
        this.nums = nums;
        this.n = nums.length;
        this.k = k;
        this.f = new HashMap<>();
        return dfs(0, 1L, 1L);
    }

    private int dfs(int i, long p, long q) {
        if (i == n) {
            return (p == k && q == 1L) ? 1 : 0;
        }

        State key = new State(i, p, q);
        if (f.containsKey(key)) {
            return f.get(key);
        }

        int res = dfs(i + 1, p, q);

        long x = nums[i];

        long g1 = gcd(p * x, q);
        res += dfs(i + 1, (p * x) / g1, q / g1);

        long g2 = gcd(p, q * x);
        res += dfs(i + 1, p / g2, (q * x) / g2);

        f.put(key, res);
        return res;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}