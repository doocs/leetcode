class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.merge(v, 1, Integer::sum);
        }
        Arrays.sort(nums);
        for (int v : nums) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    if (cnt.merge(x, -1, Integer::sum) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}
