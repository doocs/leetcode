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
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        dfs(root, p, q);
        return ans;
    }

private:
    TreeNode* ans = nullptr;

    bool dfs(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root) {
            return false;
        }
        bool l = dfs(root->left, p, q);
        bool r = dfs(root->right, p, q);
        if (l && r) {
            ans = root;
        }
        if ((l || r) && (root->val == p->val || root->val == q->val)) {
            ans = root;
        }
        return l || r || root->val == p->val || root->val == q->val;
    }
};