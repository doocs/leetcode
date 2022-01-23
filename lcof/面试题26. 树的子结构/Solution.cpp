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
    bool isSubStructure(TreeNode* A, TreeNode* B) {
        if (!A || !B) return 0;
        return dfs(A, B) || isSubStructure(A->left, B) || isSubStructure(A->right, B);
    }

    bool dfs(TreeNode* A, TreeNode* B) {
        if (!B) return 1;
        if (!A || A->val != B->val) return 0;
        return dfs(A->left, B->left) && dfs(A->right, B->right);
    }
};