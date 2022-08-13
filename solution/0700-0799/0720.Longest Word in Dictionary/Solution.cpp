class Solution {
public:
    string longestWord(vector<string>& words) {
        unordered_set<string> s(words.begin(), words.end());
        int cnt = 0;
        string ans = "";
        for (auto w : s) {
            int n = w.size();
            if (check(w, s)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w < ans)
                    ans = w;
            }
        }
        return ans;
    }

    bool check(string& word, unordered_set<string>& s) {
        for (int i = 1, n = word.size(); i < n; ++i)
            if (!s.count(word.substr(0, i)))
                return false;
        return true;
    }
};