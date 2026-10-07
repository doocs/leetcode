class Solution {
public:
    vector<string> braceExpansionII(string expression) {
        exp = std::move(expression);
        i = 0;
        set<string> ans = parseExpr();
        return vector<string>(ans.begin(), ans.end());
    }

private:
    string exp;
    int i = 0;

    set<string> parseExpr() {
        set<string> res = parseTerm();
        while (i < exp.size() && exp[i] == ',') {
            ++i;
            set<string> other = parseTerm();
            res.insert(other.begin(), other.end());
        }
        return res;
    }

    set<string> parseTerm() {
        set<string> res{""};
        while (i < (int) exp.size() && exp[i] != ',' && exp[i] != '}') {
            set<string> cur;
            if (exp[i] == '{') {
                ++i;
                cur = parseExpr();
                ++i;
            } else {
                int j = i + 1;
                while (j < (int) exp.size() && exp[j] >= 'a' && exp[j] <= 'z') {
                    ++j;
                }
                cur.insert(exp.substr(i, j - i));
                i = j;
            }
            set<string> nxt;
            for (const string& a : res) {
                for (const string& b : cur) {
                    nxt.insert(a + b);
                }
            }
            res.swap(nxt);
        }
        return res;
    }
};
