class Solution {
public:
    int minOperations(string s) {
        int n = s.size();
        int ans = INT_MAX;

        for (int k = 0; k < n; ++k) {
            int t = k;
            int i = 0, j = n - 1;

            while (i < j) {
                int x = s[(i + k) % n] - 'a';
                int y = s[(j + k) % n] - 'a';

                int d = abs(x - y);
                t += min(d, 26 - d);

                ++i;
                --j;
            }

            ans = min(ans, t);
        }

        return ans;
    }
};