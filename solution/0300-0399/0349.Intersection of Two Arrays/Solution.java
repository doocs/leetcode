class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = transfer(nums1);
        Set<Integer> s2 = transfer(nums2);
        s1.retainAll(s2);
        int[] output = new int[s1.size()];
        int i = 0;
        for (Integer e : s1) {
            output[i++] = e;
        }
        return output;
    }

    private Set<Integer> transfer(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int e : nums) {
            s.add(e);
        }
        return s;
    }
}