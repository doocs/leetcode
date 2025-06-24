int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int** threeSum(int* nums, int numsSize, int* returnSize, int** returnColumnSizes) {
    *returnSize = 0;
    int cap = 1000;
    int** ans = (int**) malloc(sizeof(int*) * cap);
    *returnColumnSizes = (int*) malloc(sizeof(int) * cap);

    qsort(nums, numsSize, sizeof(int), cmp);

    for (int i = 0; i < numsSize - 2 && nums[i] <= 0; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        int j = i + 1, k = numsSize - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (sum < 0) {
                ++j;
            } else if (sum > 0) {
                --k;
            } else {
                if (*returnSize >= cap) {
                    cap *= 2;
                    ans = (int**) realloc(ans, sizeof(int*) * cap);
                    *returnColumnSizes = (int*) realloc(*returnColumnSizes, sizeof(int) * cap);
                }
                ans[*returnSize] = (int*) malloc(sizeof(int) * 3);
                ans[*returnSize][0] = nums[i];
                ans[*returnSize][1] = nums[j];
                ans[*returnSize][2] = nums[k];
                (*returnColumnSizes)[*returnSize] = 3;
                (*returnSize)++;

                ++j;
                --k;
                while (j < k && nums[j] == nums[j - 1]) ++j;
                while (j < k && nums[k] == nums[k + 1]) --k;
            }
        }
    }
    return ans;
}
