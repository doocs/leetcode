class Solution {
public:
    vector<int> peopleIndexes(vector<vector<string>>& favoriteCompanies) {
        int n = favoriteCompanies.size();
        unordered_map<string, int> d;
        int idx = 0;
        vector<unordered_set<int>> nums(n);

        for (int i = 0; i < n; ++i) {
            for (const auto& s : favoriteCompanies[i]) {
                if (!d.contains(s)) {
                    d[s] = idx++;
                }
                nums[i].insert(d[s]);
            }
        }

        auto check = [](const unordered_set<int>& a, const unordered_set<int>& b) {
            for (int x : a) {
                if (!b.contains(x)) {
                    return false;
                }
            }
            return true;
        };

        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            bool ok = true;
            for (int j = 0; j < n && ok; ++j) {
                if (i != j && check(nums[i], nums[j])) {
                    ok = false;
                }
            }
            if (ok) {
                ans.push_back(i);
            }
        }

        return ans;
    }
};
