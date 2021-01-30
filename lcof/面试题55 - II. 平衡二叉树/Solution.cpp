/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Solution {
public:
    bool isBalanced(TreeNode* root, int* depth) {
        if (root == nullptr) {
            *depth = 0;
            return true;
        }

        int left = 0; 
        int right = 0;
        if (isBalanced(root->left, &left) 
            && isBalanced(root->right, &right)) {
            int diff = left - right;
            if (diff > 1 || diff < -1) {
                // 如果有一处不符合 -1 < diff < 1，则直接返回false
                return false;
            } else {
                // 如果符合，则记录当前深度，然后返回上一层继续计算
                *depth = max(left, right) + 1;
                return true;
            }
        }

        return false;    // 如果有一处已经确定不是平衡二叉树了，则直接返回false
    }

    bool isBalanced(TreeNode* root) {
        if (!root) {
            return true;
        }

        int depth = 0;
        return isBalanced(root, &depth);
    }
};
