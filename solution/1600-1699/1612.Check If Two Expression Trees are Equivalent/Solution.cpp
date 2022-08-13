/**
 * Definition for a binary tree node.
 * struct Node {
 *     char val;
 *     Node *left;
 *     Node *right;
 *     Node() : val(' '), left(nullptr), right(nullptr) {}
 *     Node(char x) : val(x), left(nullptr), right(nullptr) {}
 *     Node(char x, Node *left, Node *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool checkEquivalence(Node* root1, Node* root2) {
        return dfs(root1) == dfs(root2);
    }

    vector<int> dfs(Node* root) {
        vector<int> ans(26);
        if (!root) return ans;
        if (root->val == '+' || root->val == '-') {
            auto left = dfs(root->left);
            auto right = dfs(root->right);
            calc(ans, left, right, root->val);
            return ans;
        }
        ++ans[root->val - 'a'];
        return ans;
    }

    void calc(vector<int>& ans, vector<int>& left, vector<int>& right, char op) {
        for (int i = 0; i < 26; ++i)
            ans[i] = op == '+' ? left[i] + right[i] : left[i] - right[i];
    }
};