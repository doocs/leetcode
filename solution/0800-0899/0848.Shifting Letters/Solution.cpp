class Solution {
public:
    string shiftingLetters(string s, vector<int>& shifts) {
        long long t = 0;
        int n = s.size();
        for (int i = n - 1; ~i; --i) {
            t += shifts[i];
            int j = (s[i] - 'a' + t) % 26;
            s[i] = 'a' + j;
        }
        return s;
    }
};