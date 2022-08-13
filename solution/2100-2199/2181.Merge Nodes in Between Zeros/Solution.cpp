/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeNodes(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* tail = dummy;
        int s = 0;
        for (ListNode* cur = head->next; cur; cur = cur->next) {
            if (cur->val)
                s += cur->val;
            else {
                tail->next = new ListNode(s);
                tail = tail->next;
                s = 0;
            }
        }
        return dummy->next;
    }
};