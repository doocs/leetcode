int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int minimumSum(int num) {
    int nums[4] = {0};
    for (int i = 0; i < 4; i++) {
        nums[i] = num % 10;
        num /= 10;
    }
    qsort(nums, 4, sizeof(int), cmp);
    return 10 * (nums[0] + nums[1]) + nums[2] + nums[3];
}
