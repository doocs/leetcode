class Fancy {
    private static final int MOD = (int) 1e9 + 7;
    private List<Integer> nums = new ArrayList<>();
    private long a = 1, b;

    public void append(int val) {
        long x = (val - b + MOD) % MOD * qpow(a, MOD - 2) % MOD;
        nums.add((int) x);
    }

    public void addAll(int inc) {
        b = (b + inc) % MOD;
    }

    public void multAll(int m) {
        a = a * m % MOD;
        b = b * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= nums.size()) {
            return -1;
        }
        return (int) ((a * nums.get(idx) + b) % MOD);
    }

    private long qpow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
}
