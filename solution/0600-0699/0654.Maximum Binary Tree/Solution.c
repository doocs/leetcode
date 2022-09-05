/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode* construct(int* nums, int start, int end) {
    if (start >= end) {
        return NULL;
    }
    int idx = 0;
    int maxVal = -1;
    for (int i = start; i < end; i++) {
        if (nums[i] > maxVal) {
            idx = i;
            maxVal = nums[i];
        }
    }
    struct TreeNode* res = (struct TreeNode*) malloc(sizeof(struct TreeNode));
    res->val = maxVal;
    res->left = construct(nums, start, idx);
    res->right = construct(nums, idx + 1, end);
    return res;
}

struct TreeNode* constructMaximumBinaryTree(int* nums, int numsSize) {
    return construct(nums, 0, numsSize);
}
