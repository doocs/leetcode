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
    vector<int> nextLargerNodes(ListNode* head) {
        vector<int> nums;
        for (; head; head = head->next) {
            nums.push_back(head->val);
        }
        stack<int> stk;
        int n = nums.size();
        vector<int> ans(n);
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && stk.top() <= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                ans[i] = stk.top();
            }
            stk.push(nums[i]);
        }
        return ans;
    }
};