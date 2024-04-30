int firstMissingPositive(int* nums, int numsSize) {
    for (int i = 0; i < numsSize; ++i) {
        while (nums[i] >= 1 && nums[i] <= numsSize && nums[i] != nums[nums[i] - 1]) {
            swap(&nums[i], &nums[nums[i] - 1]);
        }
    }
    for (int i = 0; i < numsSize; ++i) {
        if (i + 1 != nums[i]) {
            return i + 1;
        }
    }
    return numsSize + 1;
}

void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}