class Solution {
public:
    long long shiftDistance(string s, string t, vector<int>& nextCost, vector<int>& previousCost) {
        int m = 26;
        vector<long long> s1((m << 1) + 1);
        vector<long long> s2((m << 1) + 1);
        for (int i = 0; i < (m << 1); ++i) {
            s1[i + 1] = s1[i] + nextCost[i % m];
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
        }

        long long ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            int x = s[i] - 'a';
            int y = t[i] - 'a';
            long long c1 = s1[y + (y < x ? m : 0)] - s1[x];
            long long c2 = s2[x + (x < y ? m : 0)] - s2[y];
            ans += min(c1, c2);
        }

        return ans;
    }
};
