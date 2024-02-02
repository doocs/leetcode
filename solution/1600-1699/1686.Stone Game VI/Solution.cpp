class Solution {
public:
    int stoneGameVI(vector<int>& aliceValues, vector<int>& bobValues) {
        vector<pair<int, int>> vals;
        int n = aliceValues.size();
        for (int i = 0; i < n; ++i) {
            vals.emplace_back(aliceValues[i] + bobValues[i], i);
        }
        sort(vals.rbegin(), vals.rend());
        int a = 0, b = 0;
        for (int k = 0; k < n; ++k) {
            int i = vals[k].second;
            if (k % 2 == 0) {
                a += aliceValues[i];
            } else {
                b += bobValues[i];
            }
        }
        if (a == b) {
            return 0;
        }
        return a > b ? 1 : -1;
    }
};