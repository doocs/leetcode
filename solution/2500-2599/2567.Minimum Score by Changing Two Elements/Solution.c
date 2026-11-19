#define min(a, b) (((a) < (b)) ? (a) : (b))

int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int minimizeSum(int* nums, int numsSize) {
    qsort(nums, numsSize, sizeof(int), cmp);
    int a = nums[numsSize - 3] - nums[0];
    int b = nums[numsSize - 2] - nums[1];
    int c = nums[numsSize - 1] - nums[2];
    return min(a, min(b, c));
}
