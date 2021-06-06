class Solution {
    public int reductionOperations(int[] nums) {
        TreeMap<Integer, Integer> counter = new TreeMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        int n = nums.length;
        int f = 0, res = 0;
        while (counter.size() != 0) {
            f += counter.pollFirstEntry().getValue();
            res += (n - f);
        }
        return res;
    }
}