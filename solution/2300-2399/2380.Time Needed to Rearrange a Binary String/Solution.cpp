class Solution {
public:
    int secondsToRemoveOccurrences(string s) {
        bool find = true;
        int ans = 0;
        while (find) {
            find = false;
            for (int i = 0; i < s.size() - 1; ++i) {
                if (s[i] == '0' && s[i + 1] == '1') {
                    swap(s[i], s[i + 1]);
                    ++i;
                    find = true;
                }
            }
            if (find) {
                ++ans;
            }
        }
        return ans;
    }
};