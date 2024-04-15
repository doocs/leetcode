//Approach 2: Iterative
//Time: O(n)
//Space: O(h)
class Solution {
 public:
  int sumOfLeftLeaves(TreeNode* root) {
    if (root == nullptr)
      return 0;

    int ans = 0;
    stack<TreeNode*> stack{{root}};

    while (!stack.empty()) {
      root = stack.top(), stack.pop();
      if (root->left) {
        if (root->left->left == nullptr && root->left->right == nullptr)
          ans += root->left->val;
        else
          stack.push(root->left);
      }
      if (root->right)
        stack.push(root->right);
    }

    return ans;
  }
};
