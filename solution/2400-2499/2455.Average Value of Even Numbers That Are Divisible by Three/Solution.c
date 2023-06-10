int averageValue(int* nums, int numsSize) {
    int s = 0, n = 0;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] % 6 == 0) {
            s += nums[i];
            ++n;
        }
    }
    return n == 0 ? 0 : s / n;
}