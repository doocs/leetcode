long long countSubarrays(int* nums, int numsSize, int minK, int maxK) {
    long long ans = 0;
    int j1 = -1, j2 = -1, k = -1;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] < minK || nums[i] > maxK) k = i;
        if (nums[i] == minK) j1 = i;
        if (nums[i] == maxK) j2 = i;
        int m = j1 < j2 ? j1 : j2;
        if (m > k) ans += (long long) (m - k);
    }
    return ans;
}
