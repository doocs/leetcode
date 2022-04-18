class Solution {
public:
    int maxCoins(vector<int>& piles) {
        sort(piles.begin(), piles.end());
        int ans = 0;
        for (int i = piles.size() - 2; i >= (int) piles.size() / 3; i -= 2) ans += piles[i];
        return ans;
    }
};