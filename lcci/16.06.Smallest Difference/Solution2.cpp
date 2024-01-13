class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        int i = 0, j = 0;
        long long ans = LONG_LONG_MAX;
        while (i < a.size() && j < b.size()) {
            ans = min(ans, abs(1LL * a[i] - 1LL * b[j]));
            if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
};