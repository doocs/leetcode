class Solution {
public:
    vector<string> generateValidStrings(int n, int k) {
        vector<string> ans;
        string path;

        auto dfs = [&](this auto&& dfs, int i, int tot) -> void {
            if (i >= n) {
                ans.push_back(path);
                return;
            }

            path.push_back('0');
            dfs(i + 1, tot);
            path.pop_back();

            if ((path.empty() || path.back() == '0') && tot + i <= k) {
                path.push_back('1');
                dfs(i + 1, tot + i);
                path.pop_back();
            }
        };

        dfs(0, 0);

        return ans;
    }
};