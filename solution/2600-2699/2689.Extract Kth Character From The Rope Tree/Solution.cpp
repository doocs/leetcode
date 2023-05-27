/**
 * Definition for a rope tree node.
 * struct RopeTreeNode {
 *     int len;
 *     string val;
 *     RopeTreeNode *left;
 *     RopeTreeNode *right;
 *     RopeTreeNode() : len(0), val(""), left(nullptr), right(nullptr) {}
 *     RopeTreeNode(string s) : len(0), val(std::move(s)), left(nullptr), right(nullptr) {}
 *     RopeTreeNode(int x) : len(x), val(""), left(nullptr), right(nullptr) {}
 *     RopeTreeNode(int x, RopeTreeNode *left, RopeTreeNode *right) : len(x), val(""), left(left), right(right) {}
 * };
 */
class Solution {
public:
    char getKthCharacter(RopeTreeNode* root, int k) {
        function<string(RopeTreeNode * root)> dfs = [&](RopeTreeNode* root) -> string {
            if (root == nullptr) {
                return "";
            }
            if (root->len == 0) {
                return root->val;
            }
            string left = dfs(root->left);
            string right = dfs(root->right);
            return left + right;
        };
        return dfs(root)[k - 1];
    }
};