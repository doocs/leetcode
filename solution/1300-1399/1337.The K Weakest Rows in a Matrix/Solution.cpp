class Solution {
public:
    int search(vector<int>& m) {
        int l = 0;
        int h = m.size() - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (m[mid] == 0)
                h = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }

    vector<int> kWeakestRows(vector<vector<int>>& mat, int k) {
        vector<pair<int, int>> p;
        vector<int> res;
        for (int i = 0; i < mat.size(); i++) {
            int count = search(mat[i]);
            p.push_back({count, i});
        }
        sort(p.begin(), p.end());
        for (int i = 0; i < k; i++) {
            res.push_back(p[i].second);
        }
        return res;
    }
};
