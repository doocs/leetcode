class Solution {
public:
    const vector<int> d = {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }

    bool check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t) {
            int v = t % 10;
            if (d[v] == -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
};