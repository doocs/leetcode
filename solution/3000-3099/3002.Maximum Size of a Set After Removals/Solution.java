class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int x : nums1) {
            s1.add(x);
        }
        for (int x : nums2) {
            s2.add(x);
        }
        int n = nums1.length;
        int a = 0, b = 0, c = 0;
        for (int x : s1) {
            if (!s2.contains(x)) {
                ++a;
            }
        }
        for (int x : s2) {
            if (!s1.contains(x)) {
                ++b;
            } else {
                ++c;
            }
        }
        a = Math.min(a, n / 2);
        b = Math.min(b, n / 2);
        return Math.min(a + b + c, n);
    }
}