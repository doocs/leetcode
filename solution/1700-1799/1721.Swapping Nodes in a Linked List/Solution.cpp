class Solution {
    public:
        ListNode* swapNodes(ListNode* head, int k) {
            ListNode *p1 = head;
            for (int i = 1; i < k; i++)
                p1 = p1 - > next;

            ListNode* slow = head, *fast = p1 - > next;
          
            while (fast) {
                fast = fast - > next;
                slow = slow - > next;
            }

            swap(slow - > val, p1 - > val);
            return head;
        }
};
