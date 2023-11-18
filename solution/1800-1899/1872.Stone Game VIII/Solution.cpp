class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f = stones.back();
        for (int i = n - 2; i; --i) {
            f = max(f, stones[i] - f);
        }
        return f;
    }
};