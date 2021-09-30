class Solution {
public:
    int findBottomLeftValue(TreeNode* root) {
        if (!root)
        {
            return 0;
        }

        int res = root->val;
        queue<TreeNode*> que;
        que.push(root);
        while (!que.empty())
        {
            int size = que.size();
            for (int i = 0; i < size; i++)
            {
                TreeNode* ptr = que.front();
                que.pop();
                if (i == 0)
                {
                    res = ptr->val;
                }

                if (ptr->left)
                {
                    que.push(ptr->left);
                }

                if (ptr->right)
                {
                    que.push(ptr->right);
                }
            }
        }

        return res;
    }
};
