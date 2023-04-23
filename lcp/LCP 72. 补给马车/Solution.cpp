class Solution {
public:
    vector<int> supplyWagon(vector<int>& supplies) {
        for (int h = (supplies.size() + 1) >> 1; h; --h) {
            int n = supplies.size();
            int mi = 1 << 30;
            int k = 0;
            for (int i = 0; i < n - 1; ++i) {
                int x = supplies[i] + supplies[i + 1];
                if (mi > x) {
                    mi = x;
                    k = i;
                }
            }
            vector<int> t(n - 1);
            for (int i = 0, j = 0; i < n; ++i, ++j) {
                if (i == k) {
                    t[j] = mi;
                    ++i;
                } else {
                    t[j] = supplies[i];
                }
            }
            supplies = move(t);
        }
        return supplies;
    }
};