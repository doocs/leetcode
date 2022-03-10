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
    unordered_map<int, int> indexes;

    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        for (int i = 0; i < inorder.size(); ++i) indexes[inorder[i]] = i;
        return dfs(inorder, postorder, 0, 0, inorder.size());
    }

    TreeNode* dfs(vector<int>& inorder, vector<int>& postorder, int i, int j, int n) {
        if (n <= 0) return nullptr;
        int v = postorder[j + n - 1];
        int k = indexes[v];
        TreeNode* root = new TreeNode(v);
        root->left = dfs(inorder, postorder, i, j, k - i);
        root->right = dfs(inorder, postorder, k + 1, j + k - i, n - k + i - 1);
        return root;
    }
};