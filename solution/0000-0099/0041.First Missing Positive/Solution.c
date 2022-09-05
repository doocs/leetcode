int firstMissingPositive(int* nums, int numsSize) {

    int Max = nums[0], i, *Count;

    for (i = 1; i < numsSize; i++) {
        Max = (Max < nums[i]) ? nums[i] : Max;
    }

    Count = (int*) calloc(Max + 1, sizeof(int));
    for (i = 0; i < numsSize; i++) {
        if (nums[i] > 0) {
            Count[nums[i]]++;
        }
    }

    i = 1;
    while (Count[i] != 0) {
        i++;
    }

    return i;
}
