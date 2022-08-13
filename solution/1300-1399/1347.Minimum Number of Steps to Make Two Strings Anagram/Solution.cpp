class Solution {
public:
    int minSteps(string s, string t) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        int res = 0;
        for (char c : t) {
            if (counter[c - 'a'] > 0)
                --counter[c - 'a'];
            else
                ++res;
        }
        return res;
    }
};