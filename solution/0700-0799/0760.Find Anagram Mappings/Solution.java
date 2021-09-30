class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums2.length; ++i) {
            map.computeIfAbsent(nums2[i], k -> new HashSet<>()).add(i);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            int idx = map.get(nums1[i]).iterator().next();
            res[i] = idx;
            map.get(nums1[i]).remove(idx);
        }
        return res;
    }
}