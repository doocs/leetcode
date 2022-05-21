class Solution {
public:
    int flipgame(vector<int>& fronts, vector<int>& backs) {
        unordered_set<int> s;
        int n = fronts.size();
        for (int i = 0; i < n; ++i)
            if (fronts[i] == backs[i])
                s.insert(fronts[i]);
        int ans = 9999;
        for (int& v : fronts)
            if (!s.count(v))
                ans = min(ans, v);
        for (int& v : backs)
            if (!s.count(v))
                ans = min(ans, v);
        return ans % 9999;
    }
};