class Solution {
public:
    int beautifulSubstrings(string s, int k) {
        int n = s.size();
        int vs[26]{};
        string t = "aeiou";
        for (char c : t) {
            vs[c - 'a'] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int vowels = 0;
            for (int j = i; j < n; ++j) {
                vowels += vs[s[j] - 'a'];
                int consonants = j - i + 1 - vowels;
                if (vowels == consonants && vowels * consonants % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};