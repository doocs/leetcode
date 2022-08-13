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
    int res;

    int sumEvenGrandparent(TreeNode* root) {
        res = 0;
        dfs(root, root->left);
        dfs(root, root->right);
        return res;
    }

    void dfs(TreeNode* g, TreeNode* p) {
        if (!p) return;
        if (g->val % 2 == 0) {
            if (p->left) res += p->left->val;
            if (p->right) res += p->right->val;
        }
        dfs(p, p->left);
        dfs(p, p->right);
    }
};