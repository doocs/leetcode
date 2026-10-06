class Solution {
public:
    int minRotations(int n, string s) {
        int total = 0;
        for (int i = 1; i < n; ++i) {
            int diff = abs(s[i] - s[i - 1]);
            total += min(diff, 10 - diff);
        }

        char first = s[0];
        char last = s[n - 1];
        int toFirst = min(first - '0', 10 - (first - '0'));
        int ans = total + min(last - '0', 10 - (last - '0'));

        for (int i = 1; i < n; ++i) {
            char pre = s[i - 1];
            char cur = s[i];
            int diff = abs(pre - cur);
            int edge = min(diff, 10 - diff);
            diff = abs(pre - last);
            int toLast = min(diff, 10 - diff);
            ans = min(ans, total - edge + toFirst + toLast);
        }

        return ans;
    }
};