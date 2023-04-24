class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {
        int n = names.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return heights[j] < heights[i]; });
        vector<string> ans;
        for (int i : idx) {
            ans.push_back(names[i]);
        }
        return ans;
    }
};