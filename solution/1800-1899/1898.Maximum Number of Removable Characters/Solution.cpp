class Solution {
public:
    int maximumRemovals(string s, string p, vector<int>& removable) {
        int left = 0, right = removable.size();
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(s, p, removable, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    bool check(string s, string p, vector<int>& removable, int mid) {
        int m = s.size(), n = p.size(), i = 0, j = 0;
        unordered_set<int> ids;
        for (int k = 0; k < mid; ++k) {
            ids.insert(removable[k]);
        }
        while (i < m && j < n) {
            if (ids.count(i) == 0 && s[i] == p[j]) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
};