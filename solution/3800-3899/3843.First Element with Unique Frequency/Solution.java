class Solution {
    public int firstUniqueFreq(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : cnt.values()) {
            freq.merge(v, 1, Integer::sum);
        }
        for (int x : nums) {
            if (freq.get(cnt.get(x)) == 1) {
                return x;
            }
        }
        return -1;
    }
}
