class Solution {
public:
    int findString(vector<string>& words, string s) {
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return -1;
            }
            int mid = (i + j) >> 1;
            int l = dfs(i, mid - 1);
            if (l != -1) {
                return l;
            }
            if (words[mid] == s) {
                return mid;
            }
            return dfs(mid + 1, j);
        };
        return dfs(0, words.size() - 1);
    }
};