class Solution {
public:
    vector<int> frequencySort(vector<int>& nums) {
        vector<int> cnt(201);
        for (int& v : nums) ++cnt[v + 100];
        vector<vector<int>> t;
        for (int i = 0; i < cnt.size(); ++i)
            if (cnt[i])
                t.push_back({cnt[i], -i});
        sort(t.begin(), t.end());
        vector<int> ans;
        for (auto& e : t)
            for (int j = 0; j < e[0]; ++j)
                ans.push_back(-e[1] - 100);
        return ans;
    }
};