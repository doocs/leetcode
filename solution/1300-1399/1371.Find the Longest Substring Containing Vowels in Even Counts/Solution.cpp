class Solution {
public:
    int findTheLongestSubstring(string s) {
        vector<int> pos(32, INT_MAX);
        pos[0] = -1;
        string vowels = "aeiou";
        int state = 0, ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            for (int j = 0; j < 5; ++j)
                if (s[i] == vowels[j])
                    state ^= (1 << j);
            ans = max(ans, i - pos[state]);
            pos[state] = min(pos[state], i);
        }
        return ans;
    }
};