long long minOperations(int* nums1, int nums1Size, int* nums2, int nums2Size, int k) {
    long long a = 0, b = 0;
    for (int i = 0; i < nums1Size; ++i) {
        int x = nums1[i], y = nums2[i];
        if (x == y) {
            continue;
        }
        if (k == 0 || (x - y) % k != 0) {
            return -1;
        }
        int t = (x - y) / k;
        if (t < 0) {
            a += -t;
        } else {
            b += t;
        }
    }
    return a == b ? a : -1;
}
