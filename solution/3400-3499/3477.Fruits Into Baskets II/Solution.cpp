class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        int n = fruits.size();
        vector<bool> vis(n);
        int ans = n;
        for (int x : fruits) {
            for (int i = 0; i < n; ++i) {
                if (baskets[i] >= x && !vis[i]) {
                    vis[i] = true;
                    --ans;
                    break;
                }
            }
        }
        return ans;
    }
};