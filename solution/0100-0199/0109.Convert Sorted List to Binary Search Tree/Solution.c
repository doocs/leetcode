/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct ListNode* find(struct ListNode* start, struct ListNode* end) {
    struct ListNode* fast = start;
    struct ListNode* slow = start;
    while (fast != end && fast->next != end) {
        fast = fast->next->next;
        slow = slow->next;
    }
    return slow;
}

struct TreeNode* bulid(struct ListNode* start, struct ListNode* end) {
    if (start == end) {
        return NULL;
    }
    struct ListNode* node = find(start, end);
    struct TreeNode* ans = malloc(sizeof(struct TreeNode));
    ans->val = node->val;
    ans->left = bulid(start, node);
    ans->right = bulid(node->next, end);
    return ans;
}

struct TreeNode* sortedListToBST(struct ListNode* head) {
    return bulid(head, NULL);
}
