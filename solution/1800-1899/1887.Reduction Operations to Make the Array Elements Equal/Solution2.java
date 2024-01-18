class Solution {
    public int reductionOperations(int[] nums) {
        Map<Integer, Integer> tm = new TreeMap<>();
        for (int v : nums) {
            tm.put(v, tm.getOrDefault(v, 0) + 1);
        }
        int ans = 0, cnt = 0;
        for (int v : tm.values()) {
            ans += cnt * v;
            ++cnt;
        }
        return ans;
    }
}