int differenceOfSum(int* nums, int numsSize) {
    int x = 0, y = 0;
    for (int i = 0; i < numsSize; i++) {
        int v = nums[i];
        x += v;
        while (v > 0) {
            y += v % 10;
            v /= 10;
        }
    }
    return x - y;
}
