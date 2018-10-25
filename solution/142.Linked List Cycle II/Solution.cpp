class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        if(head == NULL)return NULL;
        ListNode *fast = head;
        ListNode *slow = head;
        while(fast != NULL && fast->next != NULL){
            fast = fast->next->next;
            slow = slow->next;
            
            if(fast == slow){
                slow = head;
                while(slow != fast){
                    fast = fast->next;
                    slow = slow->next;
                }
                return fast;
            }
        }
        return NULL;//无环
    }
};
