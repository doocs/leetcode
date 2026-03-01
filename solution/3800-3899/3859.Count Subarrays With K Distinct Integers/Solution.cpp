class Solution {
public:
    long long countSubarrays(vector<int>& nums, int k, int m) {
        auto f = [&](int lim) -> long long {
            unordered_map<int, int> cnt;
            long long ans = 0;
            int l = 0;
            int t = 0;

            for (int x : nums) {
                if (++cnt[x] == m) {
                    ++t;
                }

                while (cnt.size() >= lim && t >= k) {
                    int y = nums[l++];
                    if (--cnt[y] == m - 1) {
                        --t;
                    }
                    if (cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }

                ans += l;
            }

            return ans;
        };

        return f(k) - f(k + 1);
    }
};
