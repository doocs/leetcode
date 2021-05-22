class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums1) {
            s.add(num);
        }
        Set<Integer> res = new HashSet<>();
        for (int num : nums2) {
            if (s.contains(num)) {
                res.add(num);
            }
        }
        int[] output = new int[res.size()];
        int i = 0;
        for (int num : res) {
            output[i++] = num;
        }
        return output;
    }
}