class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        if (postorder.size() < 2) return true;
        return dfs(postorder, 0, postorder.size());
    }

    bool dfs(vector<int>& postorder, int i, int n) {
        if (n <= 0) return 1;
        int v = postorder[i + n - 1];
        int j = i;
        while (j < i + n && postorder[j] < v) ++j;
        for (int k = j; k < i + n; ++k)
            if (postorder[k] < v)
                return 0;
        return dfs(postorder, i, j - i) && dfs(postorder, j, n + i - j - 1);
    }
};