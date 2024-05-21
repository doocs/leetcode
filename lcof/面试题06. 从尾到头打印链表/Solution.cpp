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
    vector<int> reversePrint(ListNode* head) {
        vector<int> ans;
        for (; head; head = head->next) {
            ans.push_back(head->val);
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};