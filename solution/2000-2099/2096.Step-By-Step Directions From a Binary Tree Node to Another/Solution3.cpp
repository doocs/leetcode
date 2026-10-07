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
    string getDirections(TreeNode* root, int startValue, int destValue) {
        TreeNode* node = lca(root, startValue, destValue);
        string pathToStart, pathToDest;
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return string(pathToStart.size(), 'U') + pathToDest;
    }

private:
    TreeNode* lca(TreeNode* root, int p, int q) {
        unordered_map<TreeNode*, TreeNode*> ret;
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(root, 0);
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (node == nullptr) {
                    continue;
                }
                if (node->val == p || node->val == q) {
                    ret[node] = node;
                    continue;
                }
                stk.emplace_back(node, 1);
                stk.emplace_back(node->right, 0);
                stk.emplace_back(node->left, 0);
            } else {
                TreeNode* left = node->left && ret.count(node->left) ? ret[node->left] : nullptr;
                TreeNode* right = node->right && ret.count(node->right) ? ret[node->right] : nullptr;
                if (left != nullptr && right != nullptr) {
                    ret[node] = node;
                } else {
                    ret[node] = left != nullptr ? left : right;
                }
            }
        }
        return ret.count(root) ? ret[root] : nullptr;
    }

    bool dfs(TreeNode* start, int x, string& path) {
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(start, 0);
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (node == nullptr) {
                    continue;
                }
                if (node->val == x) {
                    return true;
                }
                path.push_back('L');
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
            } else if (state == 1) {
                path.back() = 'R';
                stk.emplace_back(node, 2);
                stk.emplace_back(node->right, 0);
            } else {
                path.pop_back();
            }
        }
        return false;
    }
};
