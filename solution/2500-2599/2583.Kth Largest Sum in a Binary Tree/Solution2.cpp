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
    long long kthLargestLevelSum(TreeNode* root, int k) {
        vector<long long> arr;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int d) {
            if (!root) {
                return;
            }
            if (arr.size() <= d) {
                arr.push_back(0);
            }
            arr[d] += root->val;
            dfs(root->left, d + 1);
            dfs(root->right, d + 1);
        };
        dfs(root, 0);
        if (arr.size() < k) {
            return -1;
        }
        sort(arr.rbegin(), arr.rend());
        return arr[k - 1];
    }
};