class Solution {
public:
    int maxIntersectionCount(vector<int>& y) {
        int n = y.size();
        map<int, int> line;
        for (int i = 1; i < n; ++i) {
            int start = 2 * y[i - 1];
            int end = 2 * y[i] + (i == n - 1 ? 0 : (y[i] > y[i - 1] ? -1 : 1));
            ++line[min(start, end)];
            --line[max(start, end) + 1];
        }
        int ans = 0, intersection = 0;
        for (auto& [_, count] : line) {
            intersection += count;
            ans = max(ans, intersection);
        }
        return ans;
    }
};
