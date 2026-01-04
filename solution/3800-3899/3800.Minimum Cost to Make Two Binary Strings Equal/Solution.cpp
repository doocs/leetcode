class Solution {
public:
    long long minimumCost(string s, string t, int flipCost, int swapCost, int crossCost) {
        long long diff[2] = {0, 0};
        int n = s.size();
        for (int i = 0; i < n; i++) {
            if (s[i] != t[i]) {
                diff[s[i] - '0']++;
            }
        }

        long long ans = (diff[0] + diff[1]) * flipCost;

        long long mx = max(diff[0], diff[1]);
        long long mn = min(diff[0], diff[1]);
        ans = min(ans, mn * 1LL * swapCost + (mx - mn) * flipCost);

        long long avg = (mx + mn) / 2;
        ans = min(ans, (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost);

        return ans;
    }
};
