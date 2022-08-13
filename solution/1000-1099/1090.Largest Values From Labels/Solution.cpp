class Solution {
public:
    int largestValsFromLabels(vector<int>& values, vector<int>& labels, int numWanted, int useLimit) {
        int n = values.size();
        vector<pair<int, int>> p;
        for (int i = 0; i < n; ++i) p.emplace_back(values[i], labels[i]);
        sort(p.begin(), p.end());
        unordered_map<int, int> counter;
        int ans = 0, num = 0;
        for (int i = n - 1; i >= 0 && num < numWanted; --i) {
            int v = p[i].first, l = p[i].second;
            if (counter[l] < useLimit) {
                ++counter[l];
                ++num;
                ans += v;
            }
        }
        return ans;
    }
};