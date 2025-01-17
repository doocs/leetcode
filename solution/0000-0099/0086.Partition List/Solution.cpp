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
    ListNode* partition(ListNode* head, int x) {
        ListNode* l = new ListNode();
        ListNode* r = new ListNode();
        ListNode* tl = l;
        ListNode* tr = r;
        for (; head; head = head->next) {
            if (head->val < x) {
                tl->next = head;
                tl = tl->next;
            } else {
                tr->next = head;
                tr = tr->next;
            }
        }
        tr->next = nullptr;
        tl->next = r->next;
        return l->next;
    }
};
