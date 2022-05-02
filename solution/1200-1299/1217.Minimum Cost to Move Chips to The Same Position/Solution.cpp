class Solution {
public:
    int minCostToMoveChips(vector<int>& position) {
        int a = 0;
        for (auto& p : position) a += p & 1;
        int b = position.size() - a;
        return min(a, b);
    }
};