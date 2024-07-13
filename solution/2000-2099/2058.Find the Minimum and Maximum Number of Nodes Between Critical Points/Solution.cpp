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
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        vector<int> ans = {1 << 30, 0};
        int first = -1, last = -1;
        for (int i = 0; head->next->next; head = head->next, ++i) {
            int a = head->val, b = head->next->val, c = head->next->next->val;
            if (b < min(a, c) || b > max(a, c)) {
                if (last == -1) {
                    first = i;
                    last = i;
                } else {
                    ans[0] = min(ans[0], i - last);
                    last = i;
                    ans[1] = max(ans[1], last - first);
                }
            }
        }
        return first == last ? vector<int>{-1, -1} : ans;
    }
};