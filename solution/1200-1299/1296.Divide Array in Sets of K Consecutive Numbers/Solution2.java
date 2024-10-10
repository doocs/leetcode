class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h : nums) {
            tm.merge(h, 1, Integer::sum);
        }
        while (!tm.isEmpty()) {
            int v = tm.firstKey();
            for (int i = v; i < v + k; ++i) {
                if (!tm.containsKey(i)) {
                    return false;
                }
                if (tm.merge(i, -1, Integer::sum) == 0) {
                    tm.remove(i);
                }
            }
        }
        return true;
    }
}
