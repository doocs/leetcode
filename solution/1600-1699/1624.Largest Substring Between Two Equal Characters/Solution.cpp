class Solution {
public:
    int maxLengthBetweenEqualCharacters(string s) {
        vector<int> d(26, -1);
        int ans = -1;
        for (int i = 0; i < s.size(); ++i) {
            int j = s[i] - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = max(ans, i - d[j] - 1);
            }
        }
        return ans;
    }
};