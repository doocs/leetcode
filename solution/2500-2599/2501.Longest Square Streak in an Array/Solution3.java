class Solution {
    private Map<Integer, Integer> memo = new HashMap<>(); // Memoization map
    private Set<Integer> numSet = new HashSet<>();        // Set of numbers

    public int longestSquareStreak(int[] nums) {
        // Early exit if the array length is less than 2
        if (nums.length < 2)
            return -1;

        // Add numbers to the set for quick lookup
        for (int n : nums) {
            numSet.add(n);
        }

        int maxStreak = 0; // Track the maximum streak
        // Calculate the longest square streak for each number
        for (int n : nums) {
            maxStreak = Math.max(maxStreak, dfs(n));
        }

        return maxStreak < 2 ? -1 : maxStreak; // Return result
    }

    private int dfs(int x) {
        // Base case: if x is not in the set, return 0
        if (!numSet.contains(x)) {
            return 0;
        }

        // Return cached result if already computed
        if (memo.containsKey(x)) {
            return memo.get(x);
        }

        // Calculate the next number in the square streak
        long next = (long)x * x; // Use long to avoid overflow
        // If the next number exceeds the maximum allowed value, return 0
        if (next > 100000) {
            memo.put(x, 1); // Only count this number itself
            return 1;
        }

        // Recursively calculate the streak
        int streak = 1 + dfs((int)next); // Include the current number in the streak
        memo.put(x, streak);             // Cache the result
        return streak;
    }
}
