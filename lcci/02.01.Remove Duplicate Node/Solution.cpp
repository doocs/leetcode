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
    ListNode* removeDuplicateNodes(ListNode* head) {
        unordered_set<int> vis;
        ListNode* pre = new ListNode(0, head);
        while (pre->next) {
            if (vis.count(pre->next->val)) {
                pre->next = pre->next->next;
            } else {
                vis.insert(pre->next->val);
                pre = pre->next;
            }
        }
        return head;
    }
};