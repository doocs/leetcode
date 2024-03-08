import java.util.Arrays;

class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int l = 0;
        int r = changeIndices.length + 1;
        while (l < r) {
            final int m = (l + r) / 2;
            if (canMark(nums, changeIndices, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l <= changeIndices.length ? l : -1;
    }

    private boolean canMark(int[] nums, int[] changeIndices, int second) {
        int numMarked = 0;
        int decrement = 0;
        // indexToLastSecond[i] := the last second to mark the index i
        int[] indexToLastSecond = new int[nums.length];
        Arrays.fill(indexToLastSecond, -1);

        for (int i = 0; i < second; ++i) {
            indexToLastSecond[changeIndices[i] - 1] = i;
        }

        for (int i = 0; i < second; ++i) {
            // Convert to 0-indexed.
            final int index = changeIndices[i] - 1;
            if (i == indexToLastSecond[index]) {
                // Reach the last occurrence of the number.
                // So, the current second will be used to mark the index.
                if (nums[index] > decrement) {
                    // The decrement is less than the number to be marked.
                    return false;
                }
                decrement -= nums[index];
                ++numMarked;
            } else {
                ++decrement;
            }
        }
        return numMarked == nums.length;
    }
}
