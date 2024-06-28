class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), c = e.getValue();
            if (cnt.containsKey(x + 1)) {
                ans = Math.max(ans, c + cnt.get(x + 1));
            }
        }
        return ans;
    }
}