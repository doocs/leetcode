int findKth(int* nums1, int m, int i, int* nums2, int n, int j, int k) {
    if (i >= m)
        return nums2[j + k - 1];
    if (j >= n)
        return nums1[i + k - 1];
    if (k == 1)
        return nums1[i] < nums2[j] ? nums1[i] : nums2[j];

    int p = k / 2;

    int x = (i + p - 1 < m) ? nums1[i + p - 1] : INT_MAX;
    int y = (j + p - 1 < n) ? nums2[j + p - 1] : INT_MAX;

    if (x < y)
        return findKth(nums1, m, i + p, nums2, n, j, k - p);
    else
        return findKth(nums1, m, i, nums2, n, j + p, k - p);
}

double findMedianSortedArrays(int* nums1, int m, int* nums2, int n) {
    int total = m + n;
    int a = findKth(nums1, m, 0, nums2, n, 0, (total + 1) / 2);
    int b = findKth(nums1, m, 0, nums2, n, 0, (total + 2) / 2);
    return (a + b) / 2.0;
}
