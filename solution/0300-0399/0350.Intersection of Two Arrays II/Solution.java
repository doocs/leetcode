class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums1) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        List<Integer> intersection = new ArrayList<>();
        for (int num : nums2) {
            int val = counter.getOrDefault(num, 0);
            if (val > 0) {
                intersection.add(num);
                counter.put(num, val - 1);
            }
        }
        int i = 0;
        int[] res = new int[intersection.size()];
        for (int num : intersection) {
            res[i++] = num;
        }
        return res;
    }
}