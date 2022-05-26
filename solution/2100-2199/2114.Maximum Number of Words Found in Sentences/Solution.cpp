class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        int ans = 0;
        for (string& s : sentences)
            ans = max(ans, 1 + count(s, ' '));
        return ans;
    }

    int count(string s, char c) {
        int cnt = 0;
        for (char& ch : s)
            if (ch == c)
                ++cnt;
        return cnt;
    }
};