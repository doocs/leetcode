class Solution {
public:
    vector<int> p;

    bool gcdSort(vector<int>& nums) {
        int n = 100010;
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int mx = 0;
        for (auto num : nums) mx = max(mx, num);
        unordered_map<int, vector<int>> f;
        for (int i = 2; i <= mx; ++i) {
            if (!f[i].empty()) continue;
            for (int j = i; j <= mx; j += i) f[j].push_back(i);
        }
        for (int i : nums) {
            for (int j : f[i]) p[find(i)] = find(j);
        }
        vector<int> s = nums;
        sort(s.begin(), s.end());
        for (int i = 0; i < nums.size(); ++i) {
            if (s[i] != nums[i] && find(s[i]) != find(nums[i])) return false;
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};