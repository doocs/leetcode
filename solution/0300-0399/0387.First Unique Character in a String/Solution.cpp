class Solution {
public:
    int firstUniqChar(string s) {
        vector<int> counter(26);
        for (char& c : s) ++counter[c - 'a'];
        for (int i = 0; i < s.size(); ++i)
            if (counter[s[i] - 'a'] == 1)
                return i;
        return -1;
    }
};