class Solution {
public:
    void hanota(vector<int>& A, vector<int>& B, vector<int>& C) {
        function<void(int, vector<int>&, vector<int>&, vector<int>&)> dfs = [&](int n, vector<int>& a, vector<int>& b, vector<int>& c) {
            if (n == 1) {
                c.push_back(a.back());
                a.pop_back();
                return;
            }
            dfs(n - 1, a, c, b);
            c.push_back(a.back());
            a.pop_back();
            dfs(n - 1, b, a, c);
        };
        dfs(A.size(), A, B, C);
    }
};