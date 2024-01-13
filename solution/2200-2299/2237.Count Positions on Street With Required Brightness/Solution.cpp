class Solution {
public:
    int meetRequirement(int n, vector<vector<int>>& lights, vector<int>& requirement) {
        vector<int> d(100010);
        for (auto& e : lights) {
            int i = max(0, e[0] - e[1]), j = min(n - 1, e[0] + e[1]);
            ++d[i];
            --d[j + 1];
        }
        int s = 0, ans = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s >= requirement[i]) ++ans;
        }
        return ans;
    }
};