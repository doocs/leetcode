class Solution {
public:
    vector<int> printNumbers(int n) {
        vector<int> ans;
        string s;
        dfs(ans, s, 0, n);
        return ans;
    }

    void dfs(vector<int>& ans, string& s, int k, int n) {
        if (k == n) {
            int num = atoi(s.c_str());
            if (num) ans.push_back(num);
            return;
        }
        for (int i = 0; i <= 9; ++i) {
            s += i + '0';
            dfs(ans, s, k + 1, n);
            s.pop_back();
        }
    }
};
