class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> mp = new HashMap<>();
        long ans = 0;
        for (int v : tasks) {
            ++ans;
            ans = Math.max(ans, mp.getOrDefault(v, 0L));
            mp.put(v, ans + space + 1);
        }
        return ans;
    }
}