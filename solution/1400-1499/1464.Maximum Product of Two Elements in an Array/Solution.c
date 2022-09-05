int maxProduct(int* nums, int numsSize) {
    int max = 0;
    int submax = 0;
    for (int i = 0; i < numsSize; i++) {
        int num = nums[i];
        if (num > max) {
            submax = max;
            max = num;
        } else if (num > submax) {
            submax = num;
        }
    }
    return (max - 1) * (submax - 1);
}
