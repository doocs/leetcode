class Solution {
public:
    vector<long long> getDistances(vector<int>& arr) {
        unordered_map<int, vector<int>> d;
        int n = arr.size();
        for (int i = 0; i < n; ++i) d[arr[i]].push_back(i);
        vector<long long> ans(n);
        for (auto& item : d) {
            auto& v = item.second;
            int m = v.size();
            long long val = 0;
            for (int e : v) val += e;
            val -= m * v[0];
            for (int i = 0; i < v.size(); ++i) {
                int delta = i >= 1 ? v[i] - v[i - 1] : 0;
                val += i * delta - (m - i) * delta;
                ans[v[i]] = val;
            }
        }
        return ans;
    }
};