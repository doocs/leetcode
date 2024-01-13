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
    ListNode* removeNodes(ListNode* head) {
        ListNode* dummy = new ListNode(1e9, head);
        ListNode* cur = head;
        vector<ListNode*> stk = {dummy};
        for (ListNode* cur = head; cur; cur = cur->next) {
            while (stk.back()->val < cur->val) {
                stk.pop_back();
            }
            stk.back()->next = cur;
            stk.push_back(cur);
        }
        return dummy->next;
    }
};