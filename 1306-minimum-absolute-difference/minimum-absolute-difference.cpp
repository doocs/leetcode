class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        vector<vector<int>> res;
        int min = INT_MAX;
        for (int i = 1, n = arr.size(); i < n; ++i) {
            int diff = arr[i] - arr[i - 1];
            if (diff < min) {
                min = diff;
                res.clear();
            }
            if (diff == min) res.push_back({arr[i - 1], arr[i]});
        }
        return res;
    }
};