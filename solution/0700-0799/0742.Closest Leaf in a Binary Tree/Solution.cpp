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
    unordered_map<TreeNode*, vector<TreeNode*>> g;

    int findClosestLeaf(TreeNode* root, int k) {
        dfs(root, nullptr);
        queue<TreeNode*> q;
        for (auto& e : g) {
            if (e.first && e.first->val == k) {
                q.push(e.first);
                break;
            }
        }
        unordered_set<TreeNode*> seen;
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            seen.insert(node);
            if (node) {
                if (!node->left && !node->right) return node->val;
                for (auto next : g[node]) {
                    if (!seen.count(next))
                        q.push(next);
                }
            }
        }
        return 0;
    }

    void dfs(TreeNode* root, TreeNode* p) {
        if (!root) return;
        g[root].push_back(p);
        g[p].push_back(root);
        dfs(root->left, root);
        dfs(root->right, root);
    }
};