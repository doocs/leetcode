class Solution {
public:
    int sumDecoded(vector<long long>& nums) {
        const long long mod = 1000000007;
        long long ans = 0;

        for (long long v : nums) {
            long long d = v / 10;
            int w = v % 10;

            string s = to_string(d);
            long long x = stoll(s.substr(0, w));
            long long y = stoll(s.substr(w));

            ans = (ans + qpow(x, y, mod)) % mod;
        }

        return ans;
    }

private:
    long long qpow(long long x, long long y, long long mod) {
        long long res = 1;
        while (y) {
            if (y & 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            y >>= 1;
        }
        return res;
    }
};
