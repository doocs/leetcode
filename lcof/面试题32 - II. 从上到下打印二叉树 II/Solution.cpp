class Solution {
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> ans;
        if (root == NULL) return ans;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            int n = q.size();
            vector<int> v;
            for (int i = 0; i < n; ++i) {
                TreeNode* node = q.front();
                q.pop();
                v.emplace_back(node->val);
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            ans.emplace_back(v);
        }
        return ans;
    }
};
