class Solution {
public:
    int getMinimumTime(vector<int>& time, vector<vector<int>>& fruits, int limit) {
        int ans = 0;
        for (auto& f : fruits) {
            int i = f[0], num = f[1];
            ans += (num + limit - 1) / limit * time[i];
        }
        return ans;
    }
};