class Solution {
public:
    bool isValid(string code) {
        stack<string> stk;
        for (int i = 0; i < code.size(); ++i) {
            if (i && stk.empty()) return false;
            if (code.substr(i, 9) == "<![CDATA[") {
                i = code.find("]]>", i + 9);
                if (i < 0) return false;
                i += 2;
            } else if (code.substr(i, 2) == "</") {
                int j = i + 2;
                i = code.find('>', j);
                if (i < 0) return false;
                string t = code.substr(j, i - j);
                if (!check(t) || stk.empty() || stk.top() != t) return false;
                stk.pop();
            } else if (code.substr(i, 1) == "<") {
                int j = i + 1;
                i = code.find('>', j);
                if (i < 0) return false;
                string t = code.substr(j, i - j);
                if (!check(t)) return false;
                stk.push(t);
            }
        }
        return stk.empty();
    }

    bool check(string tag) {
        int n = tag.size();
        if (n < 1 || n > 9) return false;
        for (char& c : tag)
            if (!isupper(c))
                return false;
        return true;
    }
};