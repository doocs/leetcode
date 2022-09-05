class Solution {

    final int MOD = (int) (1e9 + 7);

    public int maxSum(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        if (set1.isEmpty()) {
            return (int) (Math.max(sum(nums1, 0, nums1.length - 1), sum(nums2, 0, nums2.length - 1))
                % MOD);
        }
        long res = 0;
        List<Integer> list
            = set1.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        int start1 = 0, start2 = 0, end1 = 0, end2 = 0;
        for (int common : list) {
            // 2 个数组同时到达 set
            while (nums1[end1] != common) {
                end1++;
            }
            while (nums2[end2] != common) {
                end2++;
            }
            // max
            res += Math.max(sum(nums1, start1, end1), sum(nums2, start2, end2));
            start1 = end1 + 1;
            start2 = end2 + 1;
        }

        res += Math.max(
            sum(nums1, end1 + 1, nums1.length - 1), sum(nums2, end2 + 1, nums2.length - 1));
        res %= MOD;
        return (int) res;
    }

    private long sum(int[] n, int l, int r) {
        long res = 0;
        while (l <= r) {
            res += n[l++];
        }
        return res;
    }
}