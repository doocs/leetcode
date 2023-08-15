class Solution {
public:
    int giveGem(vector<int>& gem, vector<vector<int>>& operations) {
        for (auto& op : operations) {
            int x = op[0], y = op[1];
            int v = gem[x] >> 1;
            gem[y] += v;
            gem[x] -= v;
        }
        int mx = *max_element(gem.begin(), gem.end());
        int mi = *min_element(gem.begin(), gem.end());
        return mx - mi;
    }
};