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
    int minimumOperations(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        int ans = 0;
        auto f = [](vector<int>& t) {
            int n = t.size();
            vector<int> alls(t.begin(), t.end());
            sort(alls.begin(), alls.end());
            unordered_map<int, int> m;
            int ans = 0;
            for (int i = 0; i < n; ++i) m[alls[i]] = i;
            for (int i = 0; i < n; ++i) t[i] = m[t[i]];
            for (int i = 0; i < n; ++i) {
                while (t[i] != i) {
                    swap(t[i], t[t[i]]);
                    ++ans;
                }
            }
            return ans;
        };
        while (!q.empty()) {
            vector<int> t;
            for (int n = q.size(); n; --n) {
                auto node = q.front();
                q.pop();
                t.emplace_back(node->val);
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            ans += f(t);
        }
        return ans;
    }
};