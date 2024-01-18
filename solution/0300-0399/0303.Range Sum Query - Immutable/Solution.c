typedef struct {
    int* s;
} NumArray;

NumArray* numArrayCreate(int* nums, int n) {
    int* s = malloc(sizeof(int) * (n + 1));
    s[0] = 0;
    for (int i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    NumArray* obj = malloc(sizeof(NumArray));
    obj->s = s;
    return obj;
}

int numArraySumRange(NumArray* obj, int left, int right) {
    return obj->s[right + 1] - obj->s[left];
}

void numArrayFree(NumArray* obj) {
    free(obj->s);
    free(obj);
}

/**
 * Your NumArray struct will be instantiated and called as such:
 * NumArray* obj = numArrayCreate(nums, numsSize);
 * int param_1 = numArraySumRange(obj, left, right);

 * numArrayFree(obj);
*/