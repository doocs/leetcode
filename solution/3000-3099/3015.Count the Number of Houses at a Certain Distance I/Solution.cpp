class Solution {
public:
    vector<int> countOfPairs(int n, int x, int y) {
        vector<int> ans(n);
        x--;
        y--;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = j - i;
                int b = abs(x - i) + abs(y - j) + 1;
                int c = abs(y - i) + abs(x - j) + 1;
                ans[min({a, b, c}) - 1] += 2;
            }
        }
        return ans;
    }
};