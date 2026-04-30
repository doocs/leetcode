class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i];
            if ((i & 1) == 0) {
                auto it = lower_bound(primes.begin(), primes.end(), x);
                ans += *it - x;
            } else if (isPrime[x]) {
                ans += x == 2 ? 2 : 1;
            }
        }
        return ans;
    }

private:
    static constexpr int MX = 200000;
    inline static vector<bool> isPrime = [] {
        vector<bool> p(MX + 1, true);
        p[0] = p[1] = false;
        for (int i = 2; i <= MX / i; ++i) {
            if (p[i]) {
                for (int j = i * i; j <= MX; j += i) {
                    p[j] = false;
                }
            }
        }
        return p;
    }();

    inline static vector<int> primes = [] {
        vector<int> res;
        for (int i = 2; i <= MX; ++i) {
            if (isPrime[i]) {
                res.push_back(i);
            }
        }
        return res;
    }();
};
