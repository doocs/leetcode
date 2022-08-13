class Solution {
public:
    int numberOfWeakCharacters(vector<vector<int>>& properties) {
        sort(properties.begin(), properties.end(), [&](vector<int>& a, vector<int>& b) { return a[0] == b[0] ? a[1] < b[1] : a[0] > b[0]; });
        int ans = 0, mx = 0;
        for (auto& p : properties) {
            if (mx > p[1])
                ++ans;
            else
                mx = p[1];
        }
        return ans;
    }
};