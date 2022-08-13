class Solution {
public:
    vector<int> maximumBeauty(vector<vector<int>>& items, vector<int>& queries) {
        sort(items.begin(), items.end());
        for (int i = 1; i < items.size(); ++i) items[i][1] = max(items[i - 1][1], items[i][1]);
        int n = queries.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int left = 0, right = items.size();
            while (left < right) {
                int mid = (left + right) >> 1;
                if (items[mid][0] > queries[i])
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left) ans[i] = items[left - 1][1];
        }
        return ans;
    }
};