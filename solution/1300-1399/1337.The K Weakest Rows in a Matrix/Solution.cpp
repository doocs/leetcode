class Solution {
 public:
  static bool cmp(pair<int, int> &a, pair<int, int> &b) {
    if (a.first < b.first)
      return true;
    else if (a.first == b.first) {
      return a.second < b.second;
    }
    return false;
  }

  int binary(vector<int> m) {
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

  vector<int> kWeakestRows(vector<vector<int>> &mat, int k) {
    vector<pair<int, int>> p;
    vector<int> res;

    for (int i = 0; i < mat.size(); i++) {
      int count = binary(mat[i]);

      p.push_back({count, i});
    }
    sort(p.begin(), p.end(), cmp);

    for (int i = 0; i < k; i++) {
      res.push_back(p[i].second);
    }
    return res;
  }
};
