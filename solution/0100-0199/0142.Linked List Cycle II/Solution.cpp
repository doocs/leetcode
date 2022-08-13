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
    ListNode* detectCycle(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head;
        bool hasCycle = false;
        while (!hasCycle && fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            hasCycle = slow == fast;
        }
        if (!hasCycle) {
            return nullptr;
        }
        ListNode* p = head;
        while (p != slow) {
            p = p->next;
            slow = slow->next;
        }
        return p;
    }
};