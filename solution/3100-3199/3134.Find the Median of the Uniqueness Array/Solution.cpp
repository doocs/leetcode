class Solution {
public:
    int medianOfUniquenessArray(vector<int>& nums) {
        int n = nums.size();
        using ll = long long;
        ll m = (1LL + n) * n / 2;
        int l = 0, r = n;
        auto check = [&](int mx) -> bool {
            unordered_map<int, int> cnt;
            ll k = 0;
            for (int l = 0, r = 0; r < n; ++r) {
                int x = nums[r];
                ++cnt[x];
                while (cnt.size() > mx) {
                    int y = nums[l++];
                    if (--cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }
                k += r - l + 1;
                if (k >= (m + 1) / 2) {
                    return true;
                }
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};