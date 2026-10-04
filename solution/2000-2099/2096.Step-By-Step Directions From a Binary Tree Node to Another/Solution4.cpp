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
        string pathToStart, pathToDest;
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.size() && i < pathToDest.size() && pathToStart[i] == pathToDest[i]) {
            i++;
        }
        return string(pathToStart.size() - i, 'U') + pathToDest.substr(i);
    }

private:
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
