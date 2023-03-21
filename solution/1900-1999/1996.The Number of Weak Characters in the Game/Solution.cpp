class Solution {
public:
    int numberOfWeakCharacters(vector<vector<int>>& properties) {
        sort(properties.begin(), properties.end(), [&](auto& a, auto& b) { return a[0] == b[0] ? a[1] < b[1] : a[0] > b[0]; });
        int ans = 0, mx = 0;
        for (auto& x : properties) {
            ans += x[1] < mx;
            mx = max(mx, x[1]);
        }
        return ans;
    }
};