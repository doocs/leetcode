int differenceOfSum(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += nums[i];
        while (nums[i]) {
            ans -= nums[i] % 10;
            nums[i] /= 10;
        }
    }
    return ans;
}
