class Solution {
public:
    int perfectMenu(vector<int>& materials, vector<vector<int>>& cookbooks, vector<vector<int>>& attribute, int limit) {
        int n = cookbooks.size();
        int ans = -1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int a = 0, b = 0;
            vector<int> cnt(5);
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    int x = attribute[i][0];
                    int y = attribute[i][1];
                    a += x;
                    b += y;
                    for (int j = 0; j < cookbooks[i].size(); ++j) {
                        cnt[j] += cookbooks[i][j];
                    }
                }
                bool ok = true;
                for (int i = 0; i < 5 && ok; ++i) {
                    ok = cnt[i] <= materials[i];
                }
                if (b >= limit && ans < a && ok) {
                    ans = a;
                }
            }
        }
        return ans;
    }
};