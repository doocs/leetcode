class Solution {
public:
    vector<string> ans;
    string getHappyString(int n, int k) {
        dfs("", n);
        return ans.size() < k ? "" : ans[k - 1];
    }

    void dfs(string t, int n) {
        if (t.size() == n) {
            ans.push_back(t);
            return;
        }
        for (int c = 'a'; c <= 'c'; ++c) {
            if (t.size() && t.back() == c) continue;
            t.push_back(c);
            dfs(t, n);
            t.pop_back();
        }
    }
};