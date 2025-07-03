
int cmp(const void* a, const void* b) { return *(int*) a - *(int*) b; }

int threeSumClosest(int* nums, int numsSize, int target) {
    qsort(nums, numsSize, sizeof(int), cmp);
    int closest = nums[0] + nums[1] + nums[2];

    for (int i = 0; i < numsSize - 2; i++) {
        int j = i + 1, k = numsSize - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (abs(sum - target) < abs(closest - target)) {
                closest = sum;
            }
            if (sum < target)
                j++;
            else
                k--;
        }
    }

    return closest;
}
