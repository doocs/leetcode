#define max(a, b) (a > b ? a : b)

int search(int* nums, int numsSize, int x) {
    int left = 0;
    int right = numsSize;
    while (left < right) {
        int mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

int maximumCount(int* nums, int numsSize) {
    int i = search(nums, numsSize, 1);
    int j = search(nums, numsSize, 0);
    return max(numsSize - i, j);
}