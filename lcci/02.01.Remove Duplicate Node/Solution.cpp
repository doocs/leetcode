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
        if (head == nullptr || head->next == nullptr) {
            return head;
        }
        unordered_set<int> cache = {head->val};
        ListNode* cur = head;
        for (ListNode* p = head->next; p != nullptr; p = p->next) {
            if (!cache.count(p->val)) {
                cur->next = p;
                cur = cur->next;
                cache.insert(p->val);
            }
        }
        cur->next = nullptr;
        return head;
    }
};