#include <stdlib.h>

int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    int capacity = 1;
    while (capacity < numsSize * 2) capacity <<= 1;
    int* keys = malloc(capacity * sizeof(int));
    int* vals = malloc(capacity * sizeof(int));
    char* used = calloc(capacity, sizeof(char));
    if (!keys || !vals || !used) {
        free(keys);
        free(vals);
        free(used);
        *returnSize = 0;
        return NULL;
    }
    for (int i = 0; i < numsSize; ++i) {
        int x = nums[i];
        int y = target - x;
        unsigned int h = (unsigned int) y & (capacity - 1);
        while (used[h]) {
            if (keys[h] == y) {
                int* res = malloc(2 * sizeof(int));
                res[0] = vals[h];
                res[1] = i;
                *returnSize = 2;
                free(keys);
                free(vals);
                free(used);
                return res;
            }
            h = (h + 1) & (capacity - 1);
        }
        unsigned int h2 = (unsigned int) x & (capacity - 1);
        while (used[h2]) h2 = (h2 + 1) & (capacity - 1);
        used[h2] = 1;
        keys[h2] = x;
        vals[h2] = i;
    }
    *returnSize = 0;
    free(keys);
    free(vals);
    free(used);
    return NULL;
}
