class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        unordered_map<int, int> m;
        for (int i = 0; i < alls.size(); ++i) m[alls[i]] = i + 1;
        vector<int> ans;
        for (int v : arr) ans.push_back(m[v]);
        return ans;
    }
};