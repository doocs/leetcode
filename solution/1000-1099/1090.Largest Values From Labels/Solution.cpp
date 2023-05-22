class Solution {
public:
    int largestValsFromLabels(vector<int>& values, vector<int>& labels, int numWanted, int useLimit) {
        int n = values.size();
        vector<pair<int, int>> pairs(n);
        for (int i = 0; i < n; ++i) {
            pairs[i] = {-values[i], labels[i]};
        }
        sort(pairs.begin(), pairs.end());
        unordered_map<int, int> cnt;
        int ans = 0, num = 0;
        for (int i = 0; i < n && num < numWanted; ++i) {
            int v = -pairs[i].first, l = pairs[i].second;
            if (cnt[l] < useLimit) {
                ++cnt[l];
                ++num;
                ans += v;
            }
        }
        return ans;
    }
};