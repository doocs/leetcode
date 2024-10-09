class Solution {
public:
    int maxPossibleScore(vector<int>& start, int d) {
        ranges::sort(start);
        auto check = [&](int mi) -> bool {
            long long last = LLONG_MIN;
            for (int st : start) {
                if (last + mi > st + d) {
                    return false;
                }
                last = max((long long) st, last + mi);
            }
            return true;
        };
        int l = 0, r = start.back() + d - start[0];
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
