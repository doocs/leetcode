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
    vector<ListNode*> splitCircularLinkedList(ListNode* list) {
        ListNode* a = list;
        ListNode* b = list;
        while (b->next != list && b->next->next != list) {
            a = a->next;
            b = b->next->next;
        }
        if (b->next != list) {
            b = b->next;
        }
        ListNode* list2 = a->next;
        b->next = list2;
        a->next = list;
        return {list, list2};
    }
};