class Solution {
public:
    int maxAliveYear(vector<int>& birth, vector<int>& death) {
        int base = 1900;
        int d[102]{};
        for (int i = 0; i < birth.size(); ++i) {
            int a = birth[i] - base;
            int b = death[i] - base;
            ++d[a];
            --d[b + 1];
        }
        int s = 0, mx = 0;
        int ans = 0;
        for (int i = 0; i < 102; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                ans = base + i;
            }
        }
        return ans;
    }
};