/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* leftRightDifference(int* nums, int numsSize, int* returnSize) {
    *returnSize = numsSize;
    int* ans = (int*)malloc(sizeof(int) * numsSize);

    int l = 0, r = 0;
    for (int i = 0; i < numsSize; ++i) {
        r += nums[i];
    }

    for (int i = 0; i < numsSize; ++i) {
        r -= nums[i];
        ans[i] = abs(l - r);
        l += nums[i];
    }

    return ans;
}
