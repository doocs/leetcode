class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int x : nums) {
            tm.merge(x, 1, Integer::sum);
        }
        while (!tm.isEmpty()) {
            int x = tm.firstKey();
            for (int y = x; y < x + k; ++y) {
                int t = tm.merge(y, -1, Integer::sum);
                if (t < 0) {
                    return false;
                }
                if (t == 0) {
                    tm.remove(y);
                }
            }
        }
        return true;
    }
}
