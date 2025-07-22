int firstMissingPositive(int* nums, int numsSize) {
    for (int i = 0; i < numsSize; ++i) {
        while (nums[i] > 0 && nums[i] <= numsSize && nums[i] != nums[nums[i] - 1]) {
            int j = nums[i] - 1;
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] != i + 1) {
            return i + 1;
        }
    }
    return numsSize + 1;
}
