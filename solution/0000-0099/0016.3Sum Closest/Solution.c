int cmp(const void* a, const void* b) {
    return (*(int*) a - *(int*) b);
}

int threeSumClosest(int* nums, int numsSize, int target) {
    qsort(nums, numsSize, sizeof(int), cmp);
    int ans = 1 << 30;
    for (int i = 0; i < numsSize; ++i) {
        int j = i + 1, k = numsSize - 1;
        while (j < k) {
            int t = nums[i] + nums[j] + nums[k];
            if (t == target) {
                return t;
            }
            if (abs(t - target) < abs(ans - target)) {
                ans = t;
            }
            if (t > target) {
                --k;
            } else {
                ++j;
            }
        }
    }
    return ans;
}
