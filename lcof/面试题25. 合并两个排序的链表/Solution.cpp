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
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (nullptr == l1 && nullptr == l2) {
            return nullptr;
        }

        if (nullptr == l1 || nullptr == l2) {
            return l1 == nullptr ? l2 : l1;
        }

        ListNode* node = nullptr;
        if (l1->val > l2->val) {
            node = l2;
            node->next = mergeTwoLists(l1, l2->next);
        } else {
            node = l1;
            node->next = mergeTwoLists(l1->next, l2);
        }

        return node;
    }
};