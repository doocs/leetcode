/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode* mergeNodes(struct ListNode* head) {
    struct ListNode dummy;
    struct ListNode* cur = &dummy;
    int sum = 0;
    while (head) {
        if (head->val == 0 && sum != 0) {
            cur->next = malloc(sizeof(struct ListNode));
            cur->next->val = sum;
            cur->next->next = NULL;
            cur = cur->next;
            sum = 0;
        }
        sum += head->val;
        head = head->next;
    }
    return dummy.next;
}
