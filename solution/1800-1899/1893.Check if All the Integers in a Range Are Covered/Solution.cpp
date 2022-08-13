class Solution {
public:
    bool isCovered(vector<vector<int>>& ranges, int left, int right) {
        vector<int> d(52);
        for (auto& e : ranges) {
            ++d[e[0]];
            --d[e[1] + 1];
        }
        int s = 0;
        for (int i = 0; i < d.size(); ++i) {
            s += d[i];
            if (left <= i && i <= right && s == 0) return false;
        }
        return true;
    }
};