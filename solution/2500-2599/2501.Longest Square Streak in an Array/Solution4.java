public class Solution {
    public int longestSquareStreak(int[] nums) {
        int maxStreak = -1; // Track the maximum streak
        final int MAX_NUM = (int) Math.pow(10, 5); // Maximum value for nums
        boolean[] exists = new boolean[MAX_NUM + 1]; // Track existence of numbers
        boolean[] visited = new boolean[MAX_NUM + 1]; // Track visited numbers

        // Mark existing numbers
        for (int num : nums) {
            exists[num] = true;
        }

        // Check for square streaks
        for (int i = 2; i * i <= MAX_NUM; i++) {
            if (!exists[i] || visited[i]) {
                continue; // Skip if number doesn't exist or is already visited
            }
            visited[i] = true; // Mark as visited
            int streakLength = 1; // Start streak length
            long j = (long) i * i; // Calculate square (use long to avoid overflow)

            // Continue while j is valid and exists
            while (j >= 0 && j <= MAX_NUM && exists[(int) j]) {
                visited[(int) j] = true; // Mark as visited
                streakLength++; // Increase streak length
                j = j * j; // Move to next square
            }

            // Update maximum streak if valid
            if (streakLength > 1) {
                maxStreak = Math.max(maxStreak, streakLength);
            }
        }
        return maxStreak; // Return the maximum streak found
    }
}
