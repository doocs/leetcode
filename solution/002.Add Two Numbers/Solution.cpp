class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {

        ListNode *ans_l = new ListNode(0);
        ListNode *head = ans_l;
        int tmp = 0;
        while(l1 != NULL && l2 != NULL){
            tmp += l1->val + l2->val;
            ans_l->next = new ListNode(tmp % 10);
            tmp = tmp / 10;
            ans_l = ans_l->next;
            l1 = l1->next;
            l2 = l2->next;
        }
        
        while(l1 != NULL){
            tmp += l1->val;
            ans_l->next = new ListNode(tmp % 10);
            tmp = tmp / 10;
            ans_l = ans_l->next;
            l1 = l1->next;  
        }
        
        while(l2 != NULL){
            tmp += l2->val;
            ans_l->next = new ListNode(tmp % 10);
            tmp = tmp / 10;
            ans_l = ans_l->next;
            l2 = l2->next;  
        }
        
        if(tmp)ans_l->next = new ListNode(tmp);

        return head->next;
    }
};
