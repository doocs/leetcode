class Solution {
public:
    int i = 0;
    string expr;
    unordered_map<string, vector<int>> scope;

    int evaluate(string expression) {
        expr = expression;
        return eval();
    }

    int eval() {
        if (expr[i] != '(') return islower(expr[i]) ? scope[parseVar()].back() : parseInt();
        int ans = 0;
        ++i;
        if (expr[i] == 'l') {
            i += 4;
            vector<string> vars;
            while (1) {
                string var = parseVar();
                if (expr[i] == ')') {
                    ans = scope[var].back();
                    break;
                }
                ++i;
                vars.push_back(var);
                scope[var].push_back(eval());
                ++i;
                if (!islower(expr[i])) {
                    ans = eval();
                    break;
                }
            }
            for (string v : vars) scope[v].pop_back();
        } else {
            bool add = expr[i] == 'a';
            i += add ? 4 : 5;
            int a = eval();
            ++i;
            int b = eval();
            ans = add ? a + b : a * b;
        }
        ++i;
        return ans;
    }

    string parseVar() {
        int j = i;
        while (i < expr.size() && expr[i] != ' ' && expr[i] != ')') ++i;
        return expr.substr(j, i - j);
    }

    int parseInt() {
        int sign = 1, v = 0;
        if (expr[i] == '-') {
            sign = -1;
            ++i;
        }
        while (i < expr.size() && expr[i] >= '0' && expr[i] <= '9') {
            v = v * 10 + (expr[i] - '0');
            ++i;
        }
        return sign * v;
    }
};