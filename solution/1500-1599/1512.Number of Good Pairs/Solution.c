int numIdenticalPairs(int* nums, int numsSize) {
    int cnt[101] = {0};
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += cnt[nums[i]]++;
    }
    return ans;
}
