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
    vector<ListNode*> splitListToParts(ListNode* head, int k) {
        int n = 0;
        for (ListNode* cur = head; cur != nullptr; cur = cur->next) {
            ++n;
        }
        int cnt = n / k, mod = n % k;
        vector<ListNode*> ans(k, nullptr);
        ListNode* cur = head;
        for (int i = 0; i < k && cur != nullptr; ++i) {
            ans[i] = cur;
            int m = cnt + (i < mod ? 1 : 0);
            for (int j = 1; j < m; ++j) {
                cur = cur->next;
            }
            ListNode* nxt = cur->next;
            cur->next = nullptr;
            cur = nxt;
        }
        return ans;
    }
};