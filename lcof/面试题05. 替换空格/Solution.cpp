class Solution {
public:
    string replaceSpace(string s) {
        string ans;
        for (char ch : s) {
            if (ch == ' ')
                ans += "%20";
            else
                ans += ch;
        }
        return ans;
    }
};
