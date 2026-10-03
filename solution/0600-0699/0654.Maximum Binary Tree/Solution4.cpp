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
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        struct Frame {
            int l, r, side;
            TreeNode* parent;
        };
        int n = nums.size();
        TreeNode* root = nullptr;
        vector<Frame> stk{{0, n - 1, 0, nullptr}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            if (cur.l > cur.r) {
                continue;
            }
            int i = cur.l;
            for (int j = cur.l; j <= cur.r; ++j) {
                if (nums[i] < nums[j]) {
                    i = j;
                }
            }
            TreeNode* node = new TreeNode(nums[i]);
            if (cur.parent == nullptr) {
                root = node;
            } else if (cur.side == 0) {
                cur.parent->left = node;
            } else {
                cur.parent->right = node;
            }
            stk.push_back({i + 1, cur.r, 1, node});
            stk.push_back({cur.l, i - 1, 0, node});
        }
        return root;
    }
};
