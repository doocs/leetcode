class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(b.begin(), b.end());
        long long ans = LONG_LONG_MAX;
        for (int x : a) {
            auto it = lower_bound(b.begin(), b.end(), x);
            if (it != b.end()) {
                ans = min(ans, (long long) *it - x);
            }
            if (it != b.begin()) {
                ans = min(ans, x - (long long) *prev(it));
            }
        }
        return ans;
    }
};