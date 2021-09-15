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
    vector<int> largestValues(TreeNode *root) {
        vector<int> res;
        if (!root)
        {
            return res;
        }

        deque<TreeNode *> deq;
        deq.push_back(root);
        while (!deq.empty())
        {
            int size = deq.size();
            int maxnum = INT_MIN;
            for (int i = 0; i < size; i++)
            {
                TreeNode *ptr = deq.front();
                deq.pop_front();
                if (maxnum < ptr->val)
                {
                    maxnum = ptr->val;
                }

                if (ptr->left)
                {
                    deq.push_back(ptr->left);
                }

                if (ptr->right)
                {
                    deq.push_back(ptr->right);
                }
            }

            res.push_back(maxnum);
        }

        return res;
    }
};