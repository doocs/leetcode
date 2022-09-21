class Solution {
public:
    int stoneGameVI(vector<int>& aliceValues, vector<int>& bobValues) {
        int n = aliceValues.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {aliceValues[i] + bobValues[i], i};
        }
        sort(arr.rbegin(), arr.rend());
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int j = arr[i].second;
            if (i % 2 == 0) {
                a += aliceValues[j];
            } else {
                b += bobValues[j];
            }
        }
        if (a == b) return 0;
        return a > b ? 1 : -1;
    }
};