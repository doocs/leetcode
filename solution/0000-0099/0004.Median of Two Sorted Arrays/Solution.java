class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 > len2) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
            int t = len1;
            len1 = len2;
            len2 = t;
        }

        int min = 0;
        int max = len1;

        int m = (len1 + len2 + 1) / 2;

        while (min <= max) {
            int i = (min + max) / 2;
            int j = m - i;

            if (i > min && nums1[i - 1] > nums2[j]) {
                --max;
            } else if (i < max && nums2[j - 1] > nums1[i]) {
                ++min;
            } else {

                int maxLeft = i == 0 ? nums2[j - 1] : j == 0 ? nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1]);

                if (((len1 + len2) & 1) == 1) {
                    return maxLeft;
                }

                int minRight = i == len1 ? nums2[j] : j == len2 ? nums1[i] : Math.min(nums2[j], nums1[i]);

                return (maxLeft + minRight) / 2.0;

            }

        }

        return 0;
    }
}