class Solution {
public:
    string sortVowels(string s) {
        unordered_set<char> st = {'a', 'e', 'i', 'o', 'u'};
        vector<char> vowels;
        unordered_map<char, int> cnt;
        for (char c : s) {
            if (!st.count(c)) {
                continue;
            }
            if (!cnt.count(c)) {
                vowels.push_back(c);
            }
            cnt[c]++;
        }
        sort(vowels.begin(), vowels.end(), [&](char a, char b) {
            return cnt[a] > cnt[b];
        });
        string ans = s;
        int i = 0;
        for (int k = 0; k < s.size(); k++) {
            if (!st.count(s[k])) {
                continue;
            }
            char c = vowels[i];
            ans[k] = c;
            if (--cnt[c] == 0) {
                i++;
            }
        }
        return ans;
    }
};
