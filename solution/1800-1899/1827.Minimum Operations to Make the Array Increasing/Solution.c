#define max(a, b) (((a) > (b)) ? (a) : (b))

int minOperations(int* nums, int numsSize) {
    int ans = 0;
    int mx = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += max(0, mx + 1 - nums[i]);
        mx = max(mx + 1, nums[i]);
    }
    return ans;
}
