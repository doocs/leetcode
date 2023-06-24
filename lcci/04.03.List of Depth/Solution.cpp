/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    vector<ListNode*> listOfDepth(TreeNode* tree) {
        vector<ListNode*> ans;
        queue<TreeNode*> q{{tree}};
        while (!q.empty()) {
            ListNode* dummy = new ListNode(0);
            ListNode* cur = dummy;
            for (int k = q.size(); k; --k) {
                TreeNode* node = q.front();
                q.pop();
                cur->next = new ListNode(node->val);
                cur = cur->next;
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            ans.push_back(dummy->next);
        }
        return ans;
    }
};