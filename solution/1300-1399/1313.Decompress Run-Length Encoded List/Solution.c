/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* decompressRLElist(int* nums, int numsSize, int* returnSize) {
    int n = 0;
    for (int i = 0; i < numsSize; i += 2) {
        n += nums[i];
    }
    int* ans = (int*) malloc(n * sizeof(int));
    *returnSize = n;
    int k = 0;
    for (int i = 0; i < numsSize; i += 2) {
        int freq = nums[i];
        int val = nums[i + 1];
        for (int j = 0; j < freq; j++) {
            ans[k++] = val;
        }
    }
    return ans;
}
