class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int n : counter.values()) {
            res += n * (n - 1);
        }
        return res >> 1;
    }
}