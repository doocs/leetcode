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
        if (tree == nullptr) {
            return ans;
        }
        queue<TreeNode*> q;
        q.push(tree);
        while (!q.empty()) {
            int n = q.size();
            ListNode* head = new ListNode(-1);
            ListNode* tail = head;
            for (int i = 0; i < n; ++i) {
                TreeNode* front = q.front();
                q.pop();
                ListNode* node = new ListNode(front->val);
                tail->next = node;
                tail = node;
                if (front->left != nullptr) {
                    q.push(front->left);
                }
                if (front->right != nullptr) {
                    q.push(front->right);
                }
            }
            ans.push_back(head->next);
        }
        return ans;
    }
};