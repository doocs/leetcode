class Solution {
public:
    bool hasGroupsSizeX(vector<int>& deck) {
        int cnt[10000] = {0};
        for (int& v : deck) ++cnt[v];
        int g = -1;
        for (int& v : cnt) {
            if (v) {
                g = g == -1 ? v : __gcd(g, v);
            }
        }
        return g >= 2;
    }
};