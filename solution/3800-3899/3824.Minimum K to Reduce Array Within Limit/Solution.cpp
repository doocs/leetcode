class Solution {
public:
    int minimumK(vector<int>& nums) {
        auto check = [&](int k) -> bool {
            long long t = 0;
            for (int x : nums) {
                t += (x + k - 1) / k;
            }
            return t <= 1LL * k * k;
        };

        int l = 1, r = 1e5;
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
