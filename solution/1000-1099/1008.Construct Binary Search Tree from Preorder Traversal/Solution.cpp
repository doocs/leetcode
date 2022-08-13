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
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        return dfs(preorder, 0, preorder.size() - 1);
    }

    TreeNode* dfs(vector<int>& preorder, int i, int j) {
        if (i > j || i >= preorder.size()) return nullptr;
        TreeNode* root = new TreeNode(preorder[i]);
        int left = i + 1, right = j + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preorder[mid] > preorder[i])
                right = mid;
            else
                left = mid + 1;
        }
        root->left = dfs(preorder, i + 1, left - 1);
        root->right = dfs(preorder, left, j);
        return root;
    }
};