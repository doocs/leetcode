class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> d(length);
        for (auto& e : updates) {
            int l = e[0], r = e[1], c = e[2];
            d[l] += c;
            if (r + 1 < length) d[r + 1] -= c;
        }
        for (int i = 1; i < length; ++i) d[i] += d[i - 1];
        return d;
    }
};