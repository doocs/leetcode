#define max(a, b) (((a) > (b)) ? (a) : (b))

int maximumCount(int* nums, int numsSize) {
    int count[2] = {0};
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] < 0) {
            count[0]++;
        } else if (nums[i] > 0) {
            count[1]++;
        }
    }
    return max(count[0], count[1]);
}