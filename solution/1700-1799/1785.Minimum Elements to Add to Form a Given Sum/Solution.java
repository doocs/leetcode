class Solution {
    public int minElements(int[] nums, int limit, int goal) {
        // long s = Arrays.stream(nums).asLongStream().sum();
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        long d = Math.abs(s - goal);
        return (int) ((d + limit - 1) / limit);
    }
}