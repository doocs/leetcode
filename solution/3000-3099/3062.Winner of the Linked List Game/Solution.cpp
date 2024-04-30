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
    string gameResult(ListNode* head) {
        int odd = 0, even = 0;
        for (; head != nullptr; head = head->next->next) {
            int a = head->val;
            int b = head->next->val;
            odd += a < b;
            even += a > b;
        }
        if (odd > even) {
            return "Odd";
        }
        if (odd < even) {
            return "Even";
        }
        return "Tie";
    }
};