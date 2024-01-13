class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        int cnt[1001] = {0};
        for (auto& e : boxTypes) {
            int a = e[0], b = e[1];
            cnt[b] += a;
        }
        int ans = 0;
        for (int b = 1000; b > 0 && truckSize > 0; --b) {
            int a = cnt[b];
            if (a) {
                ans += b * min(truckSize, a);
                truckSize -= a;
            }
        }
        return ans;
    }
};