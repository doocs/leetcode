class Solution {
public:
    vector<string> braceExpansionII(string expression) {
        dfs(expression);
        return vector<string>(s.begin(), s.end());
    }

private:
    set<string> s;

    void dfs(string exp) {
        int j = exp.find_first_of('}');
        if (j == string::npos) {
            s.insert(exp);
            return;
        }
        int i = exp.rfind('{', j);
        string a = exp.substr(0, i);
        string c = exp.substr(j + 1);
        stringstream ss(exp.substr(i + 1, j - i - 1));
        string b;
        while (getline(ss, b, ',')) {
            dfs(a + b + c);
        }
    }
};