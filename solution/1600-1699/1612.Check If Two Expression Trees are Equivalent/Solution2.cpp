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
        function<vector<int>(Node*)> dfs = [&](Node* root) -> vector<int> {
            vector<int> cnt(26);
            if (!root) {
                return cnt;
            }
            if (root->val == '+' || root->val == '-') {
                auto l = dfs(root->left);
                auto r = dfs(root->right);
                int k = root->val == '+' ? 1 : -1;
                for (int i = 0; i < 26; ++i) {
                    cnt[i] += l[i] + r[i] * k;
                }
            } else {
                cnt[root->val - 'a']++;
            }
            return cnt;
        };
        return dfs(root1) == dfs(root2);
    }
};