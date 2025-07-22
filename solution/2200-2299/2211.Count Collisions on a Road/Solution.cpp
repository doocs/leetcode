class Solution {
public:
    int countCollisions(string s) {
        int n = s.size();
        int l = 0, r = n - 1;
        while (l < n && s[l] == 'L') {
            ++l;
        }
        while (r >= 0 && s[r] == 'R') {
            --r;
        }
        return r - l + 1 - count(s.begin() + l, s.begin() + r + 1, 'S');
    }
};
