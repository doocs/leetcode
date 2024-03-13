class Solution {
public:
    vector<string> shortestSubstrings(vector<string>& arr) {
        int n = arr.size();
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            int m = arr[i].size();
            for (int j = 1; j <= m && ans[i].empty(); ++j) {
                for (int l = 0; l <= m - j; ++l) {
                    string sub = arr[i].substr(l, j);
                    if (ans[i].empty() || sub < ans[i]) {
                        bool ok = true;
                        for (int k = 0; k < n && ok; ++k) {
                            if (k != i && arr[k].find(sub) != string::npos) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            ans[i] = sub;
                        }
                    }
                }
            }
        }
        return ans;
    }
};