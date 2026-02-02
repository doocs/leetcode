class Solution {
public:
    int maxCapacity(vector<int>& costs, vector<int>& capacity, int budget) {
        vector<pair<int, int>> arr;
        for (int k = 0; k < costs.size(); k++) {
            int a = costs[k], b = capacity[k];
            if (a < budget) {
                arr.emplace_back(a, b);
            }
        }
        if (arr.empty()) {
            return 0;
        }
        sort(arr.begin(), arr.end());
        multiset<pair<int, int>> remain;
        for (int i = 0; i < arr.size(); i++) {
            remain.insert({arr[i].second, i});
        }
        int i = 0, j = arr.size() - 1;
        int ans = prev(remain.end())->first;
        while (i < j) {
            remain.erase(remain.find({arr[i].second, i}));
            while (i < j && arr[i].first + arr[j].first >= budget) {
                remain.erase(remain.find({arr[j].second, j}));
                j--;
            }
            if (!remain.empty()) {
                ans = max(ans, arr[i].second + prev(remain.end())->first);
            }
            i++;
        }
        return ans;
    }
};
