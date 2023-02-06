#define min(a, b) (((a) < (b)) ? (a) : (b))
#define max(a, b) (((a) > (b)) ? (a) : (b))

int minMaxGame(int* nums, int numsSize) {
    while (numsSize != 1) {
        numsSize >>= 1;
        for (int i = 0; i < numsSize; i++) {
            int a = nums[i << 1];
            int b = nums[i << 1 | 1];
            nums[i] = i & 1 ? max(a, b) : min(a, b);
        }
    }
    return nums[0];
}
