class Solution {
public:
    long long dividePlayers(vector<int>& skill) {
        int s = accumulate(skill.begin(), skill.end(), 0);
        int m = skill.size() / 2;
        if (s % m) return -1;
        int t = s / m;
        int d[1010] = {0};
        long long ans = 0;
        for (int& v : skill) {
            if (d[t - v]) {
                ans += 1ll * v * (t - v);
                --d[t - v];
                --m;
            } else {
                ++d[v];
            }
        }
        return m == 0 ? ans : -1;
    }
};