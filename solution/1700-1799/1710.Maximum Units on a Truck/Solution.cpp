class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(boxTypes.begin(), boxTypes.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] > b[1];
        });
        int ans = 0;
        for (auto& v : boxTypes) {
            int a = min(v[0], truckSize);
            truckSize -= a;
            ans += a * v[1];
            if (!truckSize) break;
        }
        return ans;
    }
};