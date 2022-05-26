class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> delta(length);
        for (auto e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) delta[e[1] + 1] -= e[2];
        }
        for (int i = 1; i < length; ++i) delta[i] += delta[i - 1];
        return delta;
    }
};