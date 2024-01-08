int pivotIndex(int* nums, int numsSize) {
    int left, right;
    left = 0;
    right = 0;

    for (int i = 0; i < numsSize; i++) {
        right += nums[i];
    }

    for (int i = 0; i < numsSize; i++) {
        right -= nums[i];
        if (right == left)
            return i;
        left += nums[i];
    }

    return -1;
}