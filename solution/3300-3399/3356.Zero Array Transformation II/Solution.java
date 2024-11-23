class Solution {
    private int x, y;

    public int minimizeMaxDifference(int[] nums) {
        int left = 0, right = (int) 1e9, result = (int) 1e9;

        boolean isValid(int maxDiff) {
            int prev = nums[0];
            int minVal = 1, maxVal = (int) 1e9;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == -1) {
                    minVal = Math.max(prev - maxDiff, 1);
                    maxVal = Math.min(prev + maxDiff, (int) 1e9);
                    if (minVal > maxVal) {
                        return false;
                    }
                    prev = (minVal + maxVal) / 2; 
                } else {
                    if (prev != -1 && Math.abs(nums[i] - prev) > maxDiff) {
                        return false;
                    }
                    prev = nums[i];
                }
            }
            x = minVal;
            y = maxVal;
            return true;
        }

        // Binary search for the minimum maximum difference
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Optimal pair: x=" + x + ", y=" + y);
        return result;
    }
}
