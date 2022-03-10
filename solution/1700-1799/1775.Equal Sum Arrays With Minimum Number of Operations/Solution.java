class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = sum(nums1);
        int s2 = sum(nums2);
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int[] freq = new int[6];
        for (int x : nums1) {
            ++freq[6 - x];
        }
        for (int x : nums2) {
            ++freq[x - 1];
        }
        int diff = s2 - s1;
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            while (freq[i] > 0 && diff > 0) {
                diff -= i;
                --freq[i];
                ++ans;
            }
        }
        return diff > 0 ? - 1 : ans;
    }

    private int sum(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        return s;
    }
}