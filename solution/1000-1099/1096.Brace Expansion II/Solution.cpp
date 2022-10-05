class Solution {
public:
    set<string> s;
    vector<string> braceExpansionII(string expression) {
        dfs(expression);
        return vector<string>(s.begin(), s.end());
    }

    void dfs(string exp) {
        int j = exp.find('}');
        if (j == -1) {
            s.insert(exp);
            return;
        }
        int i = j;
        while (exp[i] != '{') {
            --i;
        }
        string a = exp.substr(0, i);
        string c = exp.substr(j + 1);
        stringstream ss(exp.substr(i + 1, j - i - 1));
        string b;
        while (getline(ss, b, ',')) {
            dfs(a + b + c);
        }
    }
};