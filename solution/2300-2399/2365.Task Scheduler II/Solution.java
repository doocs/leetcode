class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> day = new HashMap<>();
        long ans = 0;
        for (int task : tasks) {
            ++ans;
            ans = Math.max(ans, day.getOrDefault(task, 0L));
            day.put(task, ans + space + 1);
        }
        return ans;
    }
}