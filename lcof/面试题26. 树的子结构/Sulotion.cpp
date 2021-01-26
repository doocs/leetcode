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
    bool isSubTree(TreeNode* a, TreeNode* b) {
        if (nullptr == b) {
            // 如果小树走到头，则表示ok了
            return true;
        }

        if (nullptr == a) {
            // 如果大树走到头，小树却没走到头，说明不对了
            return false;
        }

        if (a->val != b->val) {
            return false;
        }

        return isSubTree(a->left, b->left) && isSubTree(a->right, b->right);
    }

    bool isSubStructure(TreeNode* a, TreeNode* b) {
        bool ret = false;
        if (nullptr != a && nullptr != b) {
            if (a->val == b->val) {
                // 如果值相等，才进入判定
                ret = isSubTree(a, b);
            }

            if (false == ret) {
                ret = isSubStructure(a->left, b);
            }

            if (false == ret) {
                ret = isSubStructure(a->right, b);
            }
        }

        return ret;
    }
};