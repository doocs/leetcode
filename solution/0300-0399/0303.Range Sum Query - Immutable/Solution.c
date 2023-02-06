typedef struct {
    int* sums;
} NumArray;

NumArray* numArrayCreate(int* nums, int numsSize) {
    int* sums = malloc(sizeof(int) * (numsSize + 1));
    memset(sums, 0, numsSize + 1);
    for (int i = 0; i < numsSize; i++) {
        sums[i + 1] = sums[i] + nums[i];
    }
    NumArray* res = malloc(sizeof(NumArray));
    res->sums = sums;
    return res;
}

int numArraySumRange(NumArray* obj, int left, int right) {
    return obj->sums[right + 1] - obj->sums[left];
}

void numArrayFree(NumArray* obj) {
    free(obj);
}

/**
 * Your NumArray struct will be instantiated and called as such:
 * NumArray* obj = numArrayCreate(nums, numsSize);
 * int param_1 = numArraySumRange(obj, left, right);

 * numArrayFree(obj);
*/
