using ll = long long;

class Solution {
public:
    vector<bool> canEat(vector<int>& candiesCount, vector<vector<int>>& queries) {
        int n = candiesCount.size();
        vector<ll> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + candiesCount[i];
        vector<bool> ans;
        for (auto& q : queries) {
            int t = q[0], day = q[1], mx = q[2];
            ll least = day, most = 1ll * (day + 1) * mx;
            ans.emplace_back(least < s[t + 1] && most > s[t]);
        }
        return ans;
    }
};