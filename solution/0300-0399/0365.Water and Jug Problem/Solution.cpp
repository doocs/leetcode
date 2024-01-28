class Solution {
public:
    bool canMeasureWater(int x, int y, int z) {
        using pii = pair<int, int>;
        stack<pii> stk;
        stk.emplace(0, 0);
        auto hash_function = [](const pii& o) {return hash<int>()(o.first) ^ hash<int>()(o.second);};
        unordered_set<pii, decltype(hash_function)> vis(0, hash_function);
        while (stk.size()) {
            auto st = stk.top();
            stk.pop();
            if (vis.count(st)) {
                continue;
            }
            vis.emplace(st);
            auto [i, j] = st;
            if (i == z || j == z || i + j == z) {
                return true;
            }
            stk.emplace(x, j);
            stk.emplace(i, y);
            stk.emplace(0, j);
            stk.emplace(i, 0);
            int a = min(i, y - j);
            int b = min(j, x - i);
            stk.emplace(i - a, j + a);
            stk.emplace(i + b, j - b);
        }
        return false;
    }
};