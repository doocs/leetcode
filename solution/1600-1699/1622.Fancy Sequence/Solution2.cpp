class Fancy {
public:
    void append(int val) {
        long long x = (val - b + mod) % mod * qpow(a, mod - 2) % mod;
        nums.push_back(x);
    }

    void addAll(int inc) {
        b = (b + inc) % mod;
    }

    void multAll(int m) {
        a = a * m % mod;
        b = b * m % mod;
    }

    int getIndex(int idx) {
        if (idx >= nums.size()) {
            return -1;
        }
        return (a * nums[idx] + b) % mod;
    }

private:
    const int mod = 1e9 + 7;
    vector<long long> nums;
    long long a = 1, b = 0;

    long long qpow(long long x, int n) {
        long long res = 1;
        while (n) {
            if (n & 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
};
