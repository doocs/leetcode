class Solution {
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        if (!root) return {};
        vector<vector<int>> res;
        queue<TreeNode*> q{{root}};
        bool leftToRight = true;
        while (!q.empty()) {
            int size = q.size();
            vector<int> oneLevel(size);
            for (int i = 0; i < size; ++i) {
                TreeNode* t = q.front(); q.pop();
                int idx = leftToRight ? i : (size - 1 - i);
                oneLevel[idx] = t->val;
                if (t->left) q.push(t->left);
                if (t->right) q.push(t->right);
            }
            leftToRight = !leftToRight;
            res.push_back(oneLevel);
        }
        return res;
    }
};