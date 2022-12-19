int numIdenticalPairs(int *nums, int numsSize) {
    int count[101] = {0};
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += count[nums[i]]++;
    }
    return ans;
}
