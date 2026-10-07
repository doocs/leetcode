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
    int levelMedian(TreeNode* root, int level) {
        vector<int> nums;
        vector<tuple<TreeNode*, int, int>> stk{{root, 0, 0}};
        while (!stk.empty()) {
            auto [node, i, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, i, 1);
                stk.emplace_back(node->left, i + 1, 0);
                continue;
            }
            if (i == level) {
                nums.push_back(node->val);
            }
            stk.emplace_back(node->right, i + 1, 0);
        }
        return nums.empty() ? -1 : nums[nums.size() / 2];
    }
};
