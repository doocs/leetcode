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
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        auto dfs = [&](auto&& dfs, int i, int j) -> TreeNode* {
            if (i > j) {
                return nullptr;
            }
            TreeNode* root = new TreeNode(preorder[i]);
            int l = i + 1, r = j + 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (preorder[mid] > preorder[i]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            root->left = dfs(dfs, i + 1, l - 1);
            root->right = dfs(dfs, l, j);
            return root;
        };
        return dfs(dfs, 0, preorder.size() - 1);
    }
};
