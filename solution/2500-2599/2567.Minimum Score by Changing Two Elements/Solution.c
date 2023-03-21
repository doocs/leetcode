#define min(a, b) (((a) < (b)) ? (a) : (b))

int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int minimizeSum(int* nums, int numsSize) {
    qsort(nums, numsSize, sizeof(int), cmp);
    return min(nums[numsSize - 1] - nums[2], min(nums[numsSize - 2] - nums[1], nums[numsSize - 3] - nums[0]));
}
