class Solution {
public:
    int maximumPopulation(vector<vector<int>>& logs) {
        vector<int> delta(101, 0);
        int offset = 1950;
        for (auto log : logs) {
            ++delta[log[0] - offset];
            --delta[log[1] - offset];
        }
        int res = 0, mx = 0, cur = 0;
        for (int i = 0; i < delta.size(); ++i) {
            cur += delta[i];
            if (cur > mx) {
                mx = cur;
                res = i;
            }
        }
        return res + offset;
    }
};