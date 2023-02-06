class Solution {
public:
    string minRemoveToMakeValid(string s) {
        string stk;
        int x = 0;
        for (char& c : s) {
            if (c == ')' && x == 0) continue;
            if (c == '(')
                ++x;
            else if (c == ')')
                --x;
            stk.push_back(c);
        }
        string ans;
        x = 0;
        while (stk.size()) {
            char c = stk.back();
            stk.pop_back();
            if (c == '(' && x == 0) continue;
            if (c == ')')
                ++x;
            else if (c == '(')
                --x;
            ans.push_back(c);
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};