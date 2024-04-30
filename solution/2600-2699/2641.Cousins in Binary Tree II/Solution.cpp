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
    TreeNode* replaceValueInTree(TreeNode* root) {
        memset(s, 0, sizeof(s));
        dfs1(root, 0);
        root->val = 0;
        dfs2(root, 0);
        return root;
    }

private:
    int s[100010];
    void dfs1(TreeNode* root, int depth) {
        if (!root) {
            return;
        }
        s[depth] += root->val;
        dfs1(root->left, depth + 1);
        dfs1(root->right, depth + 1);
    };

    void dfs2(TreeNode* root, int depth) {
        int l = root->left ? root->left->val : 0;
        int r = root->right ? root->right->val : 0;
        int sub = l + r;
        ++depth;
        if (root->left) {
            root->left->val = s[depth] - sub;
            dfs2(root->left, depth);
        }
        if (root->right) {
            root->right->val = s[depth] - sub;
            dfs2(root->right, depth);
        }
    };
};