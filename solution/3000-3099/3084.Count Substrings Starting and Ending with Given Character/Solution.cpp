class Solution {
public:
    long long countSubstrings(string s, char c) {
        long long cnt = ranges::count(s, c);
        return cnt + cnt * (cnt - 1) / 2;
    }
};