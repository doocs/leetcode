int averageValue(int* nums, int numsSize) {
    int sum = 0;
    int n = 0;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] % 6 == 0) {
            sum += nums[i];
            n++;
        }
    }

    if (n == 0) {
        return 0;
    }
    return sum / n;
}
