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
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            long long t = 0;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                t += root->val;
                if (root->left) {
                    q.push(root->left);
                }
                if (root->right) {
                    q.push(root->right);
                }
            }
            arr.push_back(t);
        }
        if (arr.size() < k) {
            return -1;
        }
        sort(arr.rbegin(), arr.rend());
        return arr[k - 1];
    }
};