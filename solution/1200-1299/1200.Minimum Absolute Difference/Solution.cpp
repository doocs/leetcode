class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int mi = 1 << 30;
        int n = arr.size();
        for (int i = 0; i < n - 1; ++i) {
            mi = min(mi, arr[i + 1] - arr[i]);
        }
        vector<vector<int>> ans;
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i + 1] - arr[i] == mi) {
                ans.push_back({arr[i], arr[i + 1]});
            }
        }
        return ans;
    }
};