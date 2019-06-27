class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            int val = map.getOrDefault(num, 0);
            if (val > 0) {
                list.add(num);
                map.put(num, val - 1);
            }
        }
        int i = 0;
        int[] res = new int[list.size()];
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }
}
