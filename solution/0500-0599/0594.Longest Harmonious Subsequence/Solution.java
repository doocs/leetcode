class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (counter.containsKey(num + 1)) {
                ans = Math.max(ans, counter.get(num) + counter.get(num + 1));
            }
        }
        return ans;
    }
}