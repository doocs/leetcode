class Solution {
public:
    int countCharacters(vector<string>& words, string chars) {
        vector<int> counter = count(chars);
        int ans = 0;
        for (auto& word : words) {
            vector<int> cnt = count(word);
            if (check(counter, cnt)) ans += word.size();
        }
        return ans;
    }

    vector<int> count(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        return counter;
    }

    bool check(vector<int>& cnt1, vector<int>& cnt2) {
        for (int i = 0; i < 26; ++i)
            if (cnt1[i] < cnt2[i]) return false;
        return true;
    }
};