#define min(a, b) (((a) < (b)) ? (a) : (b))

int minOperations(int* nums, int numsSize, int x) {
    int target = -x;
    for (int i = 0; i < numsSize; i++) {
        target += nums[i];
    }
    if (target < 0) {
        return -1;
    }
    int ans = INT_MAX;
    int sum = 0;
    int i = 0;
    for (int j = 0; j < numsSize; j++) {
        sum += nums[j];
        while (sum > target) {
            sum -= nums[i++];
        }
        if (sum == target) {
            ans = min(ans, numsSize - 1 - (j - i));
        }
    }
    if (ans == INT_MAX) {
        return -1;
    }
    return ans;
}
