class Solution {
public:
    vector<int> peopleIndexes(vector<vector<string>>& favoriteCompanies) {
        unordered_map<string, int> d;
        int idx = 0, n = favoriteCompanies.size();
        vector<unordered_set<int>> t(n);
        for (int i = 0; i < n; ++i) {
            auto v = favoriteCompanies[i];
            for (auto& c : v) {
                if (!d.count(c)) {
                    d[c] = idx++;
                }
            }
            unordered_set<int> s;
            for (auto& c : v) {
                s.insert(d[c]);
            }
            t[i] = s;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            bool ok = true;
            for (int j = 0; j < n; ++j) {
                if (i == j) continue;
                if (check(t[i], t[j])) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.push_back(i);
            }
        }
        return ans;
    }

    bool check(unordered_set<int>& nums1, unordered_set<int>& nums2) {
        for (int v : nums1) {
            if (!nums2.count(v)) {
                return false;
            }
        }
        return true;
    }
};