//Approach 1: Recursive
//Time: O(n)
//Space: O(h)
class Solution {
 public:
  int sumOfLeftLeaves(TreeNode* root) {
    if (root == nullptr)
      return 0;

    int ans = 0;

    if (root->left) {
      if (root->left->left == nullptr && root->left->right == nullptr)
        ans += root->left->val;
      else
        ans += sumOfLeftLeaves(root->left);
    }
    ans += sumOfLeftLeaves(root->right);

    return ans;
  }
};
