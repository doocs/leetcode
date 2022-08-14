class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());
        int i = 0, j = 0;
        for (; i < g.size(); ++i) {
            while (j < s.size() && s[j] < g[i]) {
                ++j;
            }
            if (j >= s.size()) {
                break;
            }
            ++j;
        }
        return i;
    }
};