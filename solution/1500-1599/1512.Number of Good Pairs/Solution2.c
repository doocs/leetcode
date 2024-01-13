int numIdenticalPairs(int* nums, int numsSize) {
    int cnt[101] = {0};
    for (int i = 0; i < numsSize; i++) {
        cnt[nums[i]]++;
    }
    int ans = 0;
    for (int i = 0; i < 101; ++i) {
        ans += cnt[i] * (cnt[i] - 1) / 2;
    }
    return ans;
}