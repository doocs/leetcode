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
    vector<int> boundaryOfBinaryTree(TreeNode* root) {
        auto dfs = [&](auto&& dfs, vector<int>& nums, TreeNode* root, int i) -> void {
            if (!root) {
                return;
            }
            if (i == 0) {
                if (root->left != root->right) {
                    nums.push_back(root->val);
                    if (root->left) {
                        dfs(dfs, nums, root->left, i);
                    } else {
                        dfs(dfs, nums, root->right, i);
                    }
                }
            } else if (i == 1) {
                if (root->left == root->right) {
                    nums.push_back(root->val);
                } else {
                    dfs(dfs, nums, root->left, i);
                    dfs(dfs, nums, root->right, i);
                }
            } else {
                if (root->left != root->right) {
                    nums.push_back(root->val);
                    if (root->right) {
                        dfs(dfs, nums, root->right, i);
                    } else {
                        dfs(dfs, nums, root->left, i);
                    }
                }
            }
        };
        vector<int> ans = {root->val};
        if (root->left == root->right) {
            return ans;
        }
        vector<int> left, right, leaves;
        dfs(dfs, left, root->left, 0);
        dfs(dfs, leaves, root, 1);
        dfs(dfs, right, root->right, 2);
        ans.insert(ans.end(), left.begin(), left.end());
        ans.insert(ans.end(), leaves.begin(), leaves.end());
        ans.insert(ans.end(), right.rbegin(), right.rend());
        return ans;
    }
};