class Solution {
public:
    int minSetSize(vector<int>& arr) {
        unordered_map<int, int> counter;
        for (int v : arr) ++counter[v];
        vector<int> t;
        for (auto& [k, v] : counter) t.push_back(v);
        sort(t.begin(), t.end(), greater<int>());
        int ans = 0;
        int n = 0;
        for (int cnt : t) {
            n += cnt;
            ++ans;
            if (n * 2 >= arr.size()) break;
        }
        return ans;
    }
};