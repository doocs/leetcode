class Solution {
public:
    int countVowelSubstrings(string word) {
        int ans = 0;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            unordered_set<char> t;
            for (int j = i; j < n; ++j) {
                char c = word[j];
                if (!isVowel(c)) break;
                t.insert(c);
                ans += t.size() == 5;
            }
        }
        return ans;
    }

    bool isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
};