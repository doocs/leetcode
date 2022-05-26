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
    vector<int> ret;

    void getVal(ListNode* head) {
        if (head) {
            if (head->next) {
                getVal(head->next);
            } 
            ret.push_back(head->val);
        }
    }

    vector<int> reversePrint(ListNode* head) {
        getVal(head);
        return ret;
    }
};