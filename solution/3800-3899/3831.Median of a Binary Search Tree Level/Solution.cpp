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

        auto dfs = [&](this auto&& dfs, TreeNode* node, int i) -> void {
            if (!node) {
                return;
            }
            dfs(node->left, i + 1);
            if (i == level) {
                nums.push_back(node->val);
            }
            dfs(node->right, i + 1);
        };

        dfs(root, 0);
        return nums.empty() ? -1 : nums[nums.size() / 2];
    }
};
