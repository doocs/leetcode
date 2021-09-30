class Solution {
public:
    vector<int> longestCommomSubsequence(vector<vector<int>>& arrays) {
        unordered_map<int, int> counter;
        vector<int> res;
        int n = arrays.size();
        for (auto array : arrays) {
            for (auto e : array) {
                counter[e] += 1;
                if (counter[e] == n) {
                    res.push_back(e);
                }
            }
        }
        return res;
    }
};