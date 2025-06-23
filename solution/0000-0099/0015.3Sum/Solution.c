int cmp(const void* a, const void* b) { return *(int*) a - *(int*) b; }

int** threeSum(int* nums, int numsSize, int* returnSize,
    int** returnColumnSizes) {
    *returnSize = 0;
    int capacity = 100;
    int** result = malloc(capacity * sizeof(int*));
    int* colSizes = malloc(capacity * sizeof(int));
    qsort(nums, numsSize, sizeof(int), cmp);

    for (int i = 0; i < numsSize - 2; i++) {
        if (nums[i] > 0)
            break;
        if (i > 0 && nums[i] == nums[i - 1])
            continue;

        int j = i + 1, k = numsSize - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (sum < 0) {
                j++;
            } else if (sum > 0) {
                k--;
            } else {
                if (*returnSize >= capacity) {
                    capacity *= 2;
                    result = realloc(result, capacity * sizeof(int*));
                    colSizes = realloc(colSizes, capacity * sizeof(int));
                }

                result[*returnSize] = malloc(3 * sizeof(int));
                result[*returnSize][0] = nums[i];
                result[*returnSize][1] = nums[j];
                result[*returnSize][2] = nums[k];
                colSizes[*returnSize] = 3;
                (*returnSize)++;

                j++;
                k--;
                while (j < k && nums[j] == nums[j - 1])
                    j++;
                while (j < k && nums[k] == nums[k + 1])
                    k--;
            }
        }
    }

    *returnColumnSizes = colSizes;
    return result;
}
