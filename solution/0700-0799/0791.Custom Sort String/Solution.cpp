class Solution {
public:
    string customSortString(string order, string s) {
        int d[26] = {0};
        for (int i = 0; i < order.size(); ++i) d[order[i] - 'a'] = i;
        sort(s.begin(), s.end(), [&](auto a, auto b) { return d[a - 'a'] < d[b - 'a']; });
        return s;
    }
};