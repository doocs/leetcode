import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>(); // Use HashMap to store the frequency of each number
        Map<Integer, Integer> freqCount = new HashMap<>(); // Use HashMap to store the frequency of each frequency
        int maxFreq = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (count.containsKey(num)) {
                freqCount.put(count.get(num), freqCount.getOrDefault(count.get(num), 0) - 1); // Decrement the count of the previous frequency
            }
            count.put(num, count.getOrDefault(num, 0) + 1); // Increment the count of the current number
            maxFreq = Math.max(maxFreq, count.get(num)); // Update the maximum frequency
            freqCount.put(count.get(num), freqCount.getOrDefault(count.get(num), 0) + 1); // Increment the count of the current frequency

            if (maxFreq == 1) {
                ans = i + 1; // Update the answer if all elements have the same frequency (1)
            } else if (freqCount.get(maxFreq) * maxFreq + (freqCount.getOrDefault(maxFreq - 1, 0) * (maxFreq - 1)) == i + 1 && freqCount.get(maxFreq) == 1) {
                ans = i + 1; // Update the answer if there's only one element with the maximum frequency and all other elements have a frequency one less than the maximum
            } else if (freqCount.get(maxFreq) * maxFreq + 1 == i + 1 && freqCount.get(1) == 1) {
                ans = i + 1; // Update the answer if there's only one element with a frequency of 1 and all other elements have the maximum frequency
            }
        }
        return ans;
    }
}
