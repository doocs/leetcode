/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* numberOfPairs(int* nums, int numsSize, int* returnSize) {
    int count[101] = {0};
    for (int i = 0; i < numsSize; i++) {
        count[nums[i]]++;
    }
    int sum = 0;
    for (int i = 0; i < 101; i++) {
        sum += count[i] >> 1;
    }
    int* ans = malloc(sizeof(int) * 2);
    ans[0] = sum;
    ans[1] = numsSize - sum * 2;
    *returnSize = 2;
    return ans;
}
