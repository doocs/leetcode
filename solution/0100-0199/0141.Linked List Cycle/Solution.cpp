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
    bool hasCycle(ListNode *head) {
        unordered_set<ListNode*> s;
        for (; head; head = head->next) {
            if (s.contains(head)) {
                return true;
            }
            s.insert(head);
        }
        return false;
    }
};