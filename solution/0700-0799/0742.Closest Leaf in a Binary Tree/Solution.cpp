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
    int findClosestLeaf(TreeNode* root, int k) {
        unordered_map<TreeNode*, vector<TreeNode*>> g;
        function<void(TreeNode*, TreeNode*)> dfs = [&](TreeNode* root, TreeNode* fa) {
            if (root) {
                g[root].push_back(fa);
                g[fa].push_back(root);
                dfs(root->left, root);
                dfs(root->right, root);
            }
        };
        dfs(root, nullptr);
        queue<TreeNode*> q;
        unordered_set<TreeNode*> vis;
        for (auto& [node, _] : g) {
            if (node && node->val == k) {
                q.push(node);
                vis.insert(node);
            }
        }
        while (1) {
            auto node = q.front();
            q.pop();
            if (node) {
                if (node->left == node->right) {
                    return node->val;
                }
                for (auto& nxt : g[node]) {
                    if (vis.count(nxt)) {
                        continue;
                    }
                    q.push(nxt);
                    vis.insert(nxt);
                }
            }
        }
    }
};