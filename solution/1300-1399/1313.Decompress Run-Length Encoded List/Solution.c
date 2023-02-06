/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* decompressRLElist(int* nums, int numsSize, int* returnSize) {
    int size = 0;
    for (int i = 0; i < numsSize; i += 2) {
        size += nums[i];
    }
    int* ans = malloc(size * sizeof(int));
    for (int i = 0, j = 0; j < numsSize; j += 2) {
        for (int k = 0; k < nums[j]; k++) {
            ans[i++] = nums[j + 1];
        }
    }
    *returnSize = size;
    return ans;
}
