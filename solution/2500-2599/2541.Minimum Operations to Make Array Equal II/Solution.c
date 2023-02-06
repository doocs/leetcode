long long minOperations(int* nums1, int nums1Size, int* nums2, int nums2Size, int k) {
    if (k == 0) {
        for (int i = 0; i < nums1Size; i++) {
            if (nums1[i] != nums2[i]) {
                return -1;
            }
        }
        return 0;
    }
    long long sum1 = 0;
    long long sum2 = 0;
    for (int i = 0; i < nums1Size; i++) {
        long long diff = nums1[i] - nums2[i];
        sum1 += diff;
        if (diff % k != 0) {
            return -1;
        }
        sum2 += llabs(diff);
    }
    if (sum1 != 0) {
        return -1;
    }
    return sum2 / (k * 2);
}
