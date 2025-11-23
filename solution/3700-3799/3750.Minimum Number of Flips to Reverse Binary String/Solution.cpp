class Solution {
public:
    int minimumFlips(int n) {
        vector<int> s;
        while (n > 0) {
            s.push_back(n & 1);
            n >>= 1;
        }

        int m = s.size();
        int cnt = 0;
        for (int i = 0; i < m / 2; i++) {
            if (s[i] != s[m - i - 1]) {
                cnt++;
            }
        }
        return cnt * 2;
    }
};
