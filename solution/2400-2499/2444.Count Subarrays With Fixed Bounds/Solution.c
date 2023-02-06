#define max(a, b) (((a) > (b)) ? (a) : (b))
#define min(a, b) (((a) < (b)) ? (a) : (b))

long long countSubarrays(int* nums, int numsSize, int minK, int maxK) {
    long long res = 0;
    int minIndex = -1;
    int maxIndex = -1;
    int k = -1;
    for (int i = 0; i < numsSize; i++) {
        int num = nums[i];
        if (num == minK) {
            minIndex = i;
        }
        if (num == maxK) {
            maxIndex = i;
        }
        if (num < minK || num > maxK) {
            k = i;
        }
        res += max(min(minIndex, maxIndex) - k, 0);
    }
    return res;
}
