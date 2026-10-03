/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     int numChildren;
 *     struct Node** children;
 * };
 */

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

int* preorder(struct Node* root, int* returnSize) {
    int* ans = malloc(sizeof(int) * 10000);
    *returnSize = 0;
    if (!root) {
        return ans;
    }
    struct Node** stk = malloc(sizeof(struct Node*) * 10000);
    int top = 0;
    stk[top++] = root;
    while (top) {
        struct Node* node = stk[--top];
        ans[(*returnSize)++] = node->val;
        for (int j = node->numChildren - 1; j >= 0; --j) {
            stk[top++] = node->children[j];
        }
    }
    free(stk);
    return ans;
}
