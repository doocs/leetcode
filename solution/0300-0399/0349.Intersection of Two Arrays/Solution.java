class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums1) {
            s.add(num);
        }
        Set<Integer> t = new HashSet<>();
        for (int num : nums2) {
            if (s.contains(num)) {
                t.add(num);
            }
        }
        int[] res = new int[t.size()];
        int i = 0;
        for (int num : t) {
            res[i++] = num;
        }
        return res;
    }
}