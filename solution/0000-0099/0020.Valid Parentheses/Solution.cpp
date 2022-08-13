class Solution {
public:
    bool isValid(string s) {
        stack<char> q;
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (s[i] == '{' || s[i] == '[' || s[i] == '(')
                q.push(s[i]);
            else if (q.empty() || !match(q.top(), s[i]))
                return false;
            else
                q.pop();
        }
        return q.empty();
    }

private:
    bool match(char l, char r) {
        return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}');
    }
};