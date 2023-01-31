/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        unordered_map<int, int> d;
        int n = inorder.size();
        for (int i = 0; i < n; ++i) {
            d[inorder[i]] = i;
        }
        function<TreeNode*(int, int, int)> dfs = [&](int i, int j, int n) -> TreeNode* {
            if (n < 1) {
                return nullptr;
            }
            int k = d[preorder[i]];
            int l = k - j;
            TreeNode* root = new TreeNode(preorder[i]);
            root->left = dfs(i + 1, j, l);
            root->right = dfs(i + 1 + l, k + 1, n - l - 1);
            return root;
        };
        return dfs(0, 0, n);
    }
};