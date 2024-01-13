class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {
        int n = names.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(-heights[i], i);
        }
        sort(arr.begin(), arr.end());
        vector<string> ans;
        for (int i = 0; i < n; ++i) {
            ans.emplace_back(names[arr[i].second]);
        }
        return ans;
    }
};