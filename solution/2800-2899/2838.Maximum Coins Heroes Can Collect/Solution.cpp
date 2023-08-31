class Solution {
public:
    vector<long long> maximumCoins(vector<int>& heroes, vector<int>& monsters, vector<int>& coins) {
        int m = monsters.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return monsters[i] < monsters[j];
        });
        long long s[m + 1];
        s[0] = 0;
        for (int i = 1; i <= m; ++i) {
            s[i] = s[i - 1] + coins[idx[i - 1]];
        }
        vector<long long> ans;
        auto search = [&](int x) {
            int l = 0, r = m;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (monsters[idx[mid]] > x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        for (int h : heroes) {
            ans.push_back(s[search(h)]);
        }
        return ans;
    }
};