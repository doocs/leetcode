class Solution {
public:
    int minCount(vector<int>& coins) {
        int ans = 0;
        for (int coin : coins) ans += (coin + 1) / 2;
        return ans;
    }
};