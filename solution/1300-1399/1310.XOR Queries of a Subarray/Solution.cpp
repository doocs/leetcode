class Solution {
public:
    vector<int> xorQueries(vector<int>& arr, vector<vector<int>>& queries) {
        int n = arr.size();
        vector<int> xors(n + 1);
        for (int i = 0; i < n; ++i) xors[i + 1] = xors[i] ^ arr[i];
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(xors[l] ^ xors[r + 1]);
        }
        return ans;
    }
};