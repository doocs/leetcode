class Solution {
public:
    int maxDistance(int side, vector<vector<int>>& points, int k) {
        vector<long long> nums;
        for (auto& p : points) {
            int x = p[0];
            int y = p[1];
            if (x == 0) {
                nums.push_back((long long) y);
            } else if (y == side) {
                nums.push_back((long long) side + x);
            } else if (x == side) {
                nums.push_back((long long) side * 3 - y);
            } else {
                nums.push_back((long long) side * 4 - x);
            }
        }
        sort(nums.begin(), nums.end());

        auto check = [&](int lo) -> bool {
            long long total = (long long) side * 4;
            for (long long start : nums) {
                long long end = start + total - lo;
                long long cur = start;
                bool ok = true;
                for (int i = 0; i < k - 1; ++i) {
                    auto it = lower_bound(nums.begin(), nums.end(), cur + lo);
                    if (it == nums.end() || *it > end) {
                        ok = false;
                        break;
                    }
                    cur = *it;
                }
                if (ok) {
                    return true;
                }
            }
            return false;
        };

        int l = 1, r = side;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
