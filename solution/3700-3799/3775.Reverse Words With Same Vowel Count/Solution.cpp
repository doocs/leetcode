class Solution {
public:
    string reverseWords(string s) {
        stringstream ss(s);
        string w;
        ss >> w;
        int cnt = calc(w);

        string ans = w;

        while (ss >> w) {
            ans.push_back(' ');
            if (calc(w) == cnt) {
                reverse(w.begin(), w.end());
            }
            ans += w;
        }

        return ans;
    }

private:
    int calc(const string& w) {
        return count_if(w.begin(), w.end(), [](char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        });
    }
};
