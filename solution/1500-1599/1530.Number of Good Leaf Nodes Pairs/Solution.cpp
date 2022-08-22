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
    int countPairs(TreeNode* root, int distance) {
        if (!root) return 0;
        int ans = countPairs(root->left, distance) + countPairs(root->right, distance);
        vector<int> cnt1(distance);
        vector<int> cnt2(distance);
        dfs(root->left, cnt1, 1);
        dfs(root->right, cnt2, 1);
        for (int i = 0; i < distance; ++i) {
            for (int j = 0; j < distance; ++j) {
                if (i + j <= distance) {
                    ans += cnt1[i] * cnt2[j];
                }
            }
        }
        return ans;
    }

    void dfs(TreeNode* root, vector<int>& cnt, int i) {
        if (!root || i >= cnt.size()) return;
        if (!root->left && !root->right) {
            ++cnt[i];
            return;
        }
        dfs(root->left, cnt, i + 1);
        dfs(root->right, cnt, i + 1);
    }
};