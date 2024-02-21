/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        unordered_map<int, int> pos;
        int n = postorder.size();
        for (int i = 0; i < n; ++i) {
            pos[postorder[i]] = i;
        }
        function<TreeNode*(int, int, int)> dfs = [&](int i, int j, int n) -> TreeNode* {
            if (n <= 0) {
                return nullptr;
            }
            TreeNode* root = new TreeNode(preorder[i]);
            if (n == 1) {
                return root;
            }
            int k = pos[preorder[i + 1]];
            int m = k - j + 1;
            root->left = dfs(i + 1, j, m);
            root->right = dfs(i + m + 1, k + 1, n - m - 1);
            return root;
        };
        return dfs(0, 0, n);
    }
};