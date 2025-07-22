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
struct TreeNode* dfs(int* nums, int i, int j) {
    if (i > j) {
        return NULL;
    }
    int mid = (i + j) >> 1;
    struct TreeNode* left = dfs(nums, i, mid - 1);
    struct TreeNode* right = dfs(nums, mid + 1, j);
    struct TreeNode* root = (struct TreeNode*) malloc(sizeof(struct TreeNode));
    root->val = nums[mid];
    root->left = left;
    root->right = right;
    return root;
}

struct TreeNode* sortedListToBST(struct ListNode* head) {
    int size = 0;
    struct ListNode* temp = head;
    while (temp) {
        size++;
        temp = temp->next;
    }

    int* nums = (int*) malloc(size * sizeof(int));
    temp = head;
    for (int i = 0; i < size; i++) {
        nums[i] = temp->val;
        temp = temp->next;
    }

    struct TreeNode* root = dfs(nums, 0, size - 1);
    free(nums);
    return root;
}
