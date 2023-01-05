class Solution {
public:
    int maxFrequencyScore(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
        }
        long cur = 0;
        const int mod = 1e9 + 7;
        for (auto& [k, v] : cnt) {
            cur = (cur + qmi(k, v, mod)) % mod;
        }
        long ans = cur;
        for (int i = k; i < nums.size(); ++i) {
            int a = nums[i - k], b = nums[i];
            if (a != b) {
                cur += cnt[b] ? (b - 1) * qmi(b, cnt[b], mod) % mod : b;
                cur -= cnt[a] > 1 ? (a - 1) * qmi(a, cnt[a] - 1, mod) % mod : a;
                cur = (cur + mod) % mod;
                ans = max(ans, cur);
                cnt[b]++;
                cnt[a]--;
            }
        }
        return ans;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};