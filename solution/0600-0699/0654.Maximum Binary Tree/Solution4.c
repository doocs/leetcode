/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode* constructMaximumBinaryTree(int* nums, int numsSize) {
    typedef struct {
        int l, r, side;
        struct TreeNode* parent;
    } Frame;
    Frame* stk = (Frame*) malloc(sizeof(Frame) * (numsSize * 2 + 5));
    int top = 0;
    stk[top++] = (Frame) {0, numsSize, 0, NULL};
    struct TreeNode* root = NULL;
    while (top) {
        Frame cur = stk[--top];
        if (cur.l >= cur.r) {
            continue;
        }
        int idx = cur.l;
        for (int i = cur.l + 1; i < cur.r; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        struct TreeNode* node = (struct TreeNode*) malloc(sizeof(struct TreeNode));
        node->val = nums[idx];
        node->left = node->right = NULL;
        if (cur.parent == NULL) {
            root = node;
        } else if (cur.side == 0) {
            cur.parent->left = node;
        } else {
            cur.parent->right = node;
        }
        stk[top++] = (Frame) {idx + 1, cur.r, 1, node};
        stk[top++] = (Frame) {cur.l, idx, 0, node};
    }
    free(stk);
    return root;
}
