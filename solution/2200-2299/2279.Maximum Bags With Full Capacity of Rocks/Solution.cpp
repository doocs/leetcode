class Solution {
public:
    int maximumBags(vector<int>& capacity, vector<int>& rocks, int additionalRocks) {
        int n = capacity.size();
        vector<int> d(n);
        for (int i = 0; i < n; ++i) d[i] = capacity[i] - rocks[i];
        sort(d.begin(), d.end());
        int ans = 0;
        for (int& v : d) {
            if (v > additionalRocks) break;
            ++ans;
            additionalRocks -= v;
        }
        return ans;
    }
};