int singleNumber(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < 32; i++) {
        int count = 0;
        for (int j = 0; j < numsSize; j++) {
            if (nums[j] >> i & 1) {
                count++;
            }
        }
        ans |= (uint)(count % 3) << i;
    }
    return ans;
}
