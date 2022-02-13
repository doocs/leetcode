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
    unordered_map<int, int> indexes;

    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        for (int i = 0; i < inorder.size(); ++i) indexes[inorder[i]] = i;
        return dfs(preorder, inorder, 0, 0, inorder.size());
    }

    TreeNode* dfs(vector<int>& preorder, vector<int>& inorder, int i, int j, int n) {
        if (n <= 0) return nullptr;
        int v = preorder[i];
        int k = indexes[v];
        TreeNode* root = new TreeNode(v);
        root->left = dfs(preorder, inorder, i + 1, j, k - j);
        root->right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
        return root;
    }
};