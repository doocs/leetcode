bool findSubarrays(int* nums, int numsSize) {
    for (int i = 1; i < numsSize - 2; i++) {
        for (int j = i + 1; j < numsSize; j++) {
            if (nums[i - 1] + nums[i] == nums[j - 1] + nums[j]) {
                return true;
            }
        }
    }
    return false;
}
