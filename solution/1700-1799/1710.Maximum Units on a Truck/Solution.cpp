class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(boxTypes.begin(), boxTypes.end(), [](auto& a, auto& b) { return a[1] > b[1]; });
        int ans = 0;
        for (auto& e : boxTypes) {
            int a = e[0], b = e[1];
            ans += b * min(truckSize, a);
            truckSize -= a;
            if (truckSize <= 0) break;
        }
        return ans;
    }
};