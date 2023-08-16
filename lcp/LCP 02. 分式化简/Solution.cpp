class Solution {
public:
    vector<int> fraction(vector<int>& cont) {
        function<vector<int>(int)> dfs = [&](int i) {
            if (i == cont.size() - 1) {
                return vector<int>{cont[i], 1};
            }
            vector<int> next = dfs(i + 1);
            int a = next[0], b = next[1];
            int x = a * cont[i] + b;
            int y = a;
            int g = __gcd(x, y);
            return vector<int>{x / g, y / g};
        };
        return dfs(0);
    }
};