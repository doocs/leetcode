class Solution {
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        processorTime.sort((a, b) -> a - b);
        tasks.sort((a, b) -> a - b);
        int ans = 0, i = tasks.size() - 1;
        for (int t : processorTime) {
            ans = Math.max(ans, t + tasks.get(i));
            i -= 4;
        }
        return ans;
    }
}