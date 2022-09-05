/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* shuffle(int* nums, int numsSize, int n, int* returnSize) {
    int* res = (int*) malloc(sizeof(int) * n * 2);
    for (int i = 0; i < n; i++) {
        res[2 * i] = nums[i];
        res[2 * i + 1] = nums[i + n];
    }
    *returnSize = n * 2;
    return res;
}
