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
    unordered_map<int, vector<pair<int, char>>> edges;
    unordered_set<int> visited;
    string ans;

    string getDirections(TreeNode* root, int startValue, int destValue) {
        ans = "";
        traverse(root);
        string t = "";
        dfs(startValue, destValue, t);
        return ans;
    }

    void traverse(TreeNode* root) {
        if (!root) return;
        if (root->left) {
            edges[root->val].push_back({root->left->val, 'L'});
            edges[root->left->val].push_back({root->val, 'U'});
        }
        if (root->right) {
            edges[root->val].push_back({root->right->val, 'R'});
            edges[root->right->val].push_back({root->val, 'U'});
        }
        traverse(root->left);
        traverse(root->right);
    }

    void dfs(int start, int dest, string& t) {
        if (visited.count(start)) return;
        if (start == dest) {
            if (ans == "" || ans.size() > t.size()) ans = t;
            return;
        }
        visited.insert(start);
        if (edges.count(start)) {
            for (auto& item : edges[start]) {
                t += item.second;
                dfs(item.first, dest, t);
                t.pop_back();
            }
        }
    }
};