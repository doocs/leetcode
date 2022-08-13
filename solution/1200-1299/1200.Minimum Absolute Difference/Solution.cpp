class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int mi = INT_MAX;
        int n = arr.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n - 1; ++i) {
            int a = arr[i], b = arr[i + 1];
            int d = b - a;
            if (d < mi) {
                mi = d;
                ans.clear();
                ans.push_back({a, b});
            } else if (d == mi)
                ans.push_back({a, b});
        }
        return ans;
    }
};