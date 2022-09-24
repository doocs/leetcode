class Solution {
public:
    unordered_map<int, int> d{{0, 0}, {1, 1}, {8, 8}, {2, 5}, {5, 2}, {6, 9}, {9, 6}};

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
            if (!d.count(v)) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
};