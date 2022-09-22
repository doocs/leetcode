class Solution {
public:
    bool canFormArray(vector<int>& arr, vector<vector<int>>& pieces) {
        unordered_map<int, vector<int>> d;
        for (auto& p : pieces) {
            d[p[0]] = p;
        }
        for (int i = 0; i < arr.size();) {
            if (!d.count(arr[i])) {
                return false;
            }
            for (int& v : d[arr[i]]) {
                if (arr[i++] != v) {
                    return false;
                }
            }
        }
        return true;
    }
};