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
    unordered_map<TreeNode*, TreeNode*> p;
    unordered_set<int> vis;
    vector<int> ans;

    vector<int> distanceK(TreeNode* root, TreeNode* target, int k) {
        parents(root, nullptr);
        dfs(target, k);
        return ans;
    }

    void parents(TreeNode* root, TreeNode* prev) {
        if (!root) return;
        p[root] = prev;
        parents(root->left, root);
        parents(root->right, root);
    }

    void dfs(TreeNode* root, int k) {
        if (!root || vis.count(root->val)) return;
        vis.insert(root->val);
        if (k == 0) {
            ans.push_back(root->val);
            return;
        }
        dfs(root->left, k - 1);
        dfs(root->right, k - 1);
        dfs(p[root], k - 1);
    }
};