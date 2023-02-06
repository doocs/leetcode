#define max(a, b) (((a) > (b)) ? (a) : (b))

int search(int* nums, int numsSize, int target) {
    int left = 0;
    int right = numsSize;
    while (left < right) {
        int mid = (left + right) >> 1;
        if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}

int maximumCount(int* nums, int numsSize) {
    int i = search(nums, numsSize, 0);
    int j = search(nums, numsSize, 1);
    return max(i, numsSize - j);
}
